package com.github.xtwxy.scala.playground.journal.read

import java.util.Date

import akka.actor._
import akka.pattern._
import akka.util.Timeout
import com.google.protobuf.timestamp._

import scala.concurrent.duration._
import scala.util._

object Main extends App {
  val system = ActorSystem("system")
  implicit val executionContext = system.dispatcher
  implicit val requestTimeout: Timeout = FiniteDuration(20, SECONDS)
  val journalActor = system.actorOf(Props(classOf[WriteJournalActor]), WriteJournalActor.name)

  val events = (1 to 5).map(x => {
    val timestamp = new Date()
    JournalEvent(
      WriteJournalActor.name,
      Some(Timestamp(timestamp.getTime / 1000, (timestamp.getTime % 1000).toInt)),
      x
    )
  })

  events.foreach(x => journalActor ! x)
  (journalActor ? ReadJournalCommand(WriteJournalActor.name, None))
    .mapTo[JournalEvents]
    .andThen({
      case Success(journalEvents) =>
        println(journalEvents)
        system.terminate()
      case Failure(exception) =>
        println(exception)
        system.terminate()
    })
}
