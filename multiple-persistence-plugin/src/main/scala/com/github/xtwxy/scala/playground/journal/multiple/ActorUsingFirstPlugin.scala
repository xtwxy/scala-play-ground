package com.github.xtwxy.scala.playground.journal.multiple

import akka.actor._
import akka.persistence._

class ActorUsingFirstPlugin extends PersistentActor with ActorLogging {
  override def persistenceId: String = "first"

  override def journalPluginId = "first-journal"

  override def snapshotPluginId = "first-snapshot-store"

  var value: Int = 0

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
  }

  private def updateState: (JournalEvent => Unit) = {
    case e: JournalEvent =>
      value += e.value
  }
}
