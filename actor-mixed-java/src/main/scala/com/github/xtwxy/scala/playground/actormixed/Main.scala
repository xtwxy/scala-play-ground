package com.github.xtwxy.scala.playground.actormixed

import akka.actor.{ActorSystem, Props}

object Main extends App {
  val system = ActorSystem("mixed-lang")
  val mixedActor = system.actorOf(Props[MixedActor], "mixed-actor")
}
