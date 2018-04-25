package com.github.xtwxy.scala.playground.journal.multiple

import akka.actor._
import akka.pattern._
import akka.persistence._
import akka.persistence.cassandra.query.scaladsl.CassandraReadJournal
import akka.persistence.query._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

class ActorUsingSecondPlugin extends PersistentActor with ActorLogging {
  override def persistenceId: String = "second"

  override def journalPluginId = "second-journal"

  override def snapshotPluginId = "second-snapshot-store"

  implicit val executionContext = context.system.dispatcher
  implicit val materializer = ActorMaterializer()

  var value: Int = 0

  val cassandraQueries = PersistenceQuery(context.system)
    .readJournalFor[CassandraReadJournal]("second-query-journal")

  override def receiveRecover: Receive = {
    case evt: JournalEvent =>
      updateState(evt)
    case SnapshotOffer(_, e: JournalEvent) =>
      value = e.value
    case _: RecoveryCompleted =>
    case x => log.info("unknown: {}", x)
  }

  override def receiveCommand: Receive = {
    case e: JournalEvent =>
      persist(e)(updateState)
    case c: ReadJournalCommand =>
      cassandraQueries
        .currentEventsByPersistenceId(persistenceId, Long.MinValue, Long.MaxValue)
        .runWith(Sink.seq)
        .mapTo[Seq[EventEnvelope]]
        .map(s => JournalEvents(s.map(e => e.event.asInstanceOf[JournalEvent])))
        .pipeTo(sender())
  }

  private def updateState: (JournalEvent => Unit) = {
    case e: JournalEvent =>
      value += e.value
  }
}
