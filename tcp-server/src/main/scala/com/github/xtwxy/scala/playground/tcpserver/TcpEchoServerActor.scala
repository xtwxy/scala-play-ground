package com.github.xtwxy.scala.playground.tcpserver

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, Props, Stash}
import akka.io.{IO, Tcp}
import akka.io.Tcp._

object TcpEchoServerActor {
  def props(remote: InetSocketAddress): Props = Props(classOf[TcpEchoServerActor], remote)
}

class TcpEchoServerActor(remote: InetSocketAddress) extends Actor with Stash with ActorLogging {
  implicit val system = context.system

  try {
    IO(Tcp) ! Bind(self, remote)
  } catch {
    case ex: Throwable =>
      log.error("{}", ex)
      system.terminate()
  }
  override def receive: Receive = {
    case b @ Bound(localAddress) ⇒
      context.parent ! b
      log.info("bound to address: {}", localAddress)
    case CommandFailed(_: Bind) ⇒
      context stop self
      context.system.terminate()
    case Connected(remote, local) ⇒
      val handler = context.actorOf(Props[TcpEchoHandlerActor])
      val connection = sender()
      connection ! Register(handler)
      log.info("client {} connected at {}", remote, local)
    case x =>
      sender() ! x
      log.info("unhandled message: {} from {}, default send it back.", x, sender())
  }
}
