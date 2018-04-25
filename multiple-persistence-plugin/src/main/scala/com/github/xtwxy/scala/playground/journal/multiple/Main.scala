package com.github.xtwxy.scala.playground.journal.multiple

import java.util.concurrent.TimeUnit

import akka.actor._
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.Await

object Main extends App {
  val system = ActorSystem("system")
  val actors = Seq(
    system.actorOf(Props(classOf[ActorUsingDefaultPlugin])),
    system.actorOf(Props(classOf[ActorUsingFirstPlugin])),
    system.actorOf(Props(classOf[ActorUsingSecondPlugin]))
  )

  actors.foreach(_ ! JournalEvent("hello", None, 1))

  implicit val timeout: Timeout = Timeout(30, TimeUnit.SECONDS)
  val result = actors
    .map(_ ? ReadJournalCommand("hello", None))
    .map(Await.result(_, timeout.duration))
    .collect(PartialFunction(x => x))
    .map(_ => system.terminate())
}
