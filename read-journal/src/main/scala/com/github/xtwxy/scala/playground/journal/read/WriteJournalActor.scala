package com.github.xtwxy.scala.playground.journal.read

import akka.actor.{ActorLogging, Props}
import akka.persistence.PersistentActor
import akka.persistence.query.PersistenceQuery
import akka.persistence.query.journal.leveldb.scaladsl.LeveldbReadJournal
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, Sink}

import scala.util._

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

  val readJournal = PersistenceQuery(context.system)
    .readJournalFor[LeveldbReadJournal](LeveldbReadJournal.Identifier)
    .eventsByPersistenceId(persistenceId)
    .toMat(Sink.foreach(x => println(x)))(Keep.right)
    .run()

  override def receiveRecover: Receive = {
    case e: JournalEvent =>
      updateState(e)
    case x =>
      log.info("unhandled: {}", x)
  }

  override def receiveCommand: Receive = {
    case c: ReadJournalCommand =>
      JournalEvents(Seq())
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
