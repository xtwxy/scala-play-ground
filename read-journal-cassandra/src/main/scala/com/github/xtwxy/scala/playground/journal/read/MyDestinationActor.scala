package com.github.xtwxy.scala.playground.journal.read

import akka.actor.{Actor, ActorLogging, Props}
import akka.persistence.journal.Tagged
import akka.persistence.{PersistentActor, RecoveryCompleted, SnapshotMetadata, SnapshotOffer}

object MyDestinationActor {
  def props = Props(classOf[MyDestinationActor])
  def name: String = "my-destination-actor-v1.0.0"
  def tag: String = "1.0.0"
}

class MyDestinationActor extends PersistentActor
  with ActorLogging {

  override def receiveRecover: Receive = {
    case e: DeliverJournalEvent =>
      updateState(e)
    case Tagged(e, _) => updateState(e)
    case SnapshotOffer(metadata: SnapshotMetadata, snapshot: Any) =>
    case _: RecoveryCompleted =>
    case x =>
      log.info("unhandled recover message: {}", x)
  }

  override def receiveCommand: Receive = {
    case x: DeliverJournalEvent =>
      log.info("receive delivered: {}", x.deliveryId)
      persist(Tagged(x, Set(MyDestinationActor.tag)))(updateStateWithTagged)
    case x =>
      log.info("unhandled command: {}", x)
  }

  override def persistenceId: String = self.path.name

  private def updateStateWithTagged: (Any => Unit) = {
    case Tagged(e, _) => updateState(e)
    case e => updateState(e)
  }

  private def updateState: (Any => Unit) = {
    case x: DeliverJournalEvent =>
      sender() ! ConfirmDeliveryCommand(x.deliveryId)
      log.info("update state to delivered: {}", x.deliveryId)
    case x =>
      log.info("unhandled update state: {}", x)
  }
}
