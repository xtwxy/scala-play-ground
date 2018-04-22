package com.github.xtwxy.scala.playground.journal.read

import akka.actor._
import akka.pattern._
import akka.persistence._
import akka.persistence.cassandra.query.scaladsl.CassandraReadJournal
import akka.persistence.query._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

object WriteJournalActor {
  def props: Props = Props(classOf[WriteJournalActor])

  def name: String = "write-journal-actor"
}

class WriteJournalActor extends PersistentActor
  with ActorLogging {
  implicit val executionContext = context.system.dispatcher
  implicit val materializer = ActorMaterializer()

  override def persistenceId: String = WriteJournalActor.name

  var value: Int = 0

  val cassandraQueries = PersistenceQuery(context.system)
    .readJournalFor[CassandraReadJournal](CassandraReadJournal.Identifier)

  override def receiveRecover: Receive = {
    case e: JournalEvent =>
      updateState(e)
    case SnapshotOffer(metadata: SnapshotMetadata, snapshot: Any) =>
    case _: RecoveryCompleted =>
    case x =>
      log.info("unhandled: {}", x)
  }

  override def receiveCommand: Receive = {
    case c: ReadJournalCommand =>
      cassandraQueries
        .currentEventsByPersistenceId(persistenceId, Long.MinValue, Long.MaxValue)
        .runWith(Sink.seq)
        .mapTo[Seq[EventEnvelope]]
        .map(s => JournalEvents(s.map(e => e.event.asInstanceOf[JournalEvent])))
        .pipeTo(sender())
    case e: JournalEvent =>
      persist(e)(updateState)
    case x =>
      log.info("unhandled: {}", x)
  }

  private def updateState: (JournalEvent => Unit) = {
    case e: JournalEvent =>
      value += e.value
    case x =>
      log.info("unhandled: {}", x)
  }
}
