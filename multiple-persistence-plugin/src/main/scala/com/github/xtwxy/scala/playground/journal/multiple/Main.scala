package com.github.xtwxy.scala.playground.journal.multiple

import akka.actor._

object Main extends App {
  val system = ActorSystem("system")
  val actors = Seq(
    system.actorOf(Props(classOf[ActorUsingDefaultPlugin])),
    system.actorOf(Props(classOf[ActorUsingFirstPlugin])),
    system.actorOf(Props(classOf[ActorUsingSecondPlugin]))
  )

  actors.foreach(_ ! JournalEvent("hello", None, 1))
}
