package com.github.xtwxy.scala.playground.journal.read

import akka.actor._
import akka.pattern._
import akka.persistence._
import akka.persistence.cassandra.query.scaladsl.CassandraReadJournal
import akka.persistence.query._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

object WriteJournalActor {
  def props(destination: ActorSelection): Props = Props(new WriteJournalActor(destination))

  def name: String = "write-journal-actor-v1.0.0"
}

class WriteJournalActor(destination: ActorSelection) extends AtLeastOnceDelivery
  with ActorLogging {
  implicit val executionContext = context.system.dispatcher
  implicit val materializer = ActorMaterializer()

  override def persistenceId: String = WriteJournalActor.name

  var value: Int = 0

  val cassandraQueries = PersistenceQuery(context.system)
    .readJournalFor[CassandraReadJournal](CassandraReadJournal.Identifier)

  cassandraQueries
    .persistenceIds()
    .flatMapConcat(s => cassandraQueries.eventsByPersistenceId(s, Long.MinValue, Long.MaxValue))
    .runWith(Sink.foreach(e => printf("(%s, %s, %s, %s)\n", e.persistenceId, e.offset, e.sequenceNr, e.event.getClass.getName)))

  override def receiveRecover: Receive = {
    case e: JournalEvent =>
      updateState(e)
    case e: DeliveryConfirmedEvent =>
      updateState(e)
    case SnapshotOffer(metadata: SnapshotMetadata, snapshot: Any) =>
    case _: RecoveryCompleted =>
    case x =>
      log.info("unhandled recover message: {}", x)
  }

  override def receiveCommand: Receive = {
    case c: ReadJournalCommand =>
      cassandraQueries
        .currentEventsByPersistenceId(persistenceId, Long.MinValue, Long.MaxValue)
        .runWith(Sink.seq)
        .mapTo[Seq[EventEnvelope]]
        .map(s => JournalEvents(
          s.filter(e => e.event.isInstanceOf[JournalEvent])
          .map(e => e.event.asInstanceOf[JournalEvent])
        ))
        .pipeTo(sender())
    case e: JournalEvent =>
      persist(e)(updateState)
    case c: ConfirmDeliveryCommand =>
      persist(DeliveryConfirmedEvent(c.deliveryId))(updateState)
    case x =>
      log.info("unhandled command: {}", x)
  }

  private def updateState: (Any => Unit) = {
    case e: JournalEvent =>
      value += e.value
      deliver(destination)(deliveryId => DeliverJournalEvent(deliveryId, e.id, e.ts, e.value))
    case e: DeliveryConfirmedEvent =>
      log.info("confirmed: {}", e.deliveryId)
      confirmDelivery(e.deliveryId)
    case x =>
      log.info("unhandled update state: {}", x)
  }
}
