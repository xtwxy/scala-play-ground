package com.github.xtwxy.scala.playground.journal.read

import akka.actor.{Actor, ActorLogging, Props}

object MyDestinationActor {
  def props = Props(classOf[MyDestinationActor])
}

class MyDestinationActor extends Actor
  with ActorLogging {
  override def receive: Receive = {
    case x: DeliverJournalEvent =>
      log.info("delivered: {}", x.deliveryId)
      sender() ! ConfirmDeliveryCommand(x.deliveryId)
  }
}
