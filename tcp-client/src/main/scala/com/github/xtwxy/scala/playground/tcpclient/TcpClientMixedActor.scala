package com.github.xtwxy.scala.playground.tcpclient

import java.net.InetSocketAddress

import akka.actor.{AbstractActor, Actor, Props}

object TcpClientMixedActor {
  def props(remote: InetSocketAddress): Props = Props(classOf[TcpClientMixedActor], remote)
}
class TcpClientMixedActor(remote: InetSocketAddress) extends Actor {
  val javaReceive: AbstractActor.Receive = new JavaReceive(this).initial(remote)

  override def receive: Receive = {
    case x => javaReceive.onMessage(x)
  }
}
