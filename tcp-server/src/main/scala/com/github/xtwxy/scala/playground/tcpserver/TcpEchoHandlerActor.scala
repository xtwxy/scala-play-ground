package com.github.xtwxy.scala.playground.tcpserver

import akka.actor.{Actor, ActorLogging}
import akka.io.Tcp.{CommandFailed, ConnectionClosed, Received}
import akka.io.TcpMessage
import akka.util.ByteString

class TcpEchoHandlerActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case x: CommandFailed =>
      context.parent ! x
      context.stop(self)
    case data: ByteString =>
      sender() ! TcpMessage.write(data)
    case Received(data) =>
      log.info("reply to sender: {}", data)
      sender() ! TcpMessage.write(data)
    case x: ConnectionClosed =>
      context.parent ! x
      context.stop(self)
    case x => log.info("unhandled message: {}", x)
  }
}
