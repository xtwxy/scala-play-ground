package com.github.xtwxy.scala.playground.tcpserver

import java.net.InetSocketAddress

import akka.actor.ActorSystem

object Main extends App {
  if(args.length < 2) {
    println("Usage: <cmd> <host> <port>")
  } else {
    val system = ActorSystem("echo-system")
    val server = system.actorOf(TcpEchoServerActor.props(new InetSocketAddress(args(0), Integer.parseInt(args(1)))), "mixed-actor")
  }
}
