package com.github.xtwxy.scala.playground.clusterevent

import akka.actor.{ActorSystem, Props}

object Main extends App {
  val system = ActorSystem("cluster-event")
  val mixedActor = system.actorOf(Props[ClusterEventListenerActor], "cluster-event-listener-actor")
}
