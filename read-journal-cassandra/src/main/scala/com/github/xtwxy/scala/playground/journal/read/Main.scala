package com.github.xtwxy.scala.playground.journal.read

import java.util.Date

import akka.actor._
import akka.util.Timeout
import com.google.protobuf.timestamp._

import scala.concurrent.duration._

object Main extends App {
  val system = ActorSystem("system")
  implicit val executionContext = system.dispatcher
  implicit val requestTimeout: Timeout = FiniteDuration(20, SECONDS)
  val destinationActor = system.actorOf(
    MyDestinationActor.props,
    MyDestinationActor.name
  )
  val journalActor = system.actorOf(
    WriteJournalActor.props(system.actorSelection(destinationActor.path)),
    WriteJournalActor.name
  )

  val events = (1 to 5).map(x => {
    val timestamp = new Date()
    JournalEvent(
      WriteJournalActor.name,
      Some(Timestamp(timestamp.getTime / 1000, (timestamp.getTime % 1000).toInt)),
      x
    )
  })

  events.foreach(x => journalActor ! x)
}
