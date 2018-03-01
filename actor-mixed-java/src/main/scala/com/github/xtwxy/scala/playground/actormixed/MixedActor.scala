package com.github.xtwxy.scala.playground.actormixed

import akka.actor.{AbstractActor, Actor}

class MixedActor extends Actor {
  val javaReceive: AbstractActor.Receive = JavaReceive.create(this)

  override def preStart(): Unit = {
    super.preStart()
    self ! "hello"
  }

  override def receive: Receive = {
    case x => javaReceive.onMessage(x)
  }
}
