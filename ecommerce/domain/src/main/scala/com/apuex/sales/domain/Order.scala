/** ***************************************************
  * * This file is 100% ***GENERATED***, DO NOT EDIT! **
  * ****************************************************/
package com.apuex.sales.domain

import akka.actor._
import akka.persistence._
import akka.util.Timeout._
import akka.util._
import com.apuex.sales.message._
import com.google.protobuf.timestamp.Timestamp

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object Order {
  def props = Props[Order]
}

class Order extends PersistentActor with ActorLogging {
  override def persistenceId: String = s"${self.path.name}"

  implicit def requestTimeout: Timeout = FiniteDuration(20, SECONDS)

  implicit def executionContext: ExecutionContext = context.dispatcher

  var orderId: String = null
  var orderTime: Timestamp = null
  var orderItems: Seq[OrderItemVo] = Seq()

  override def receiveRecover: Receive = {
    case evt: Event =>
      updateState(evt)
    case SnapshotOffer(_, x: OrderVo) =>
      this.orderId = x.orderId
      this.orderTime = x.orderTime
      this.orderItems = x.orderItems
    case x: RecoveryCompleted => log.info("RECOVER Completed: {} {}", this, x)
    case x => log.info("RECOVER Discarded: {} {}", this, x)
  }

  override def receiveCommand: Receive = {
    case cmd: CreateOrderCmd =>
      persist(CreateOrderEvt(cmd.userId, cmd.orderId, cmd.orderTime, cmd.orderItems))(updateState)
      sender() ! OrderVo(this.orderId, this.orderTime, this.orderItems)
    case cmd: UpdateOrderCmd =>
      persist(UpdateOrderEvt(cmd.userId, cmd.orderId, cmd.orderTime, cmd.orderItems))(updateState)
      sender() ! OrderVo(this.orderId, this.orderTime, this.orderItems)
    case cmd: DeleteOrderCmd =>
      persist(DeleteOrderEvt(cmd.userId, cmd.orderId))(updateState)
      sender() ! OrderVo(this.orderId, this.orderTime, this.orderItems)
    case _: RetrieveOrderCmd =>
      sender() ! OrderVo(this.orderId, this.orderTime, this.orderItems)
    case cmd: AddOrderItemsCmd =>
      persist(AddOrderItemsEvt(cmd.userId, cmd.orderId, cmd.orderItems))(updateState)
      sender() ! OrderVo(this.orderId, this.orderTime, this.orderItems)
    case cmd: RemoveOrderItemsCmd =>
      persist(RemoveOrderItemsEvt(cmd.userId, cmd.orderId, cmd.orderItems))(updateState)
      sender() ! OrderVo(this.orderId, this.orderTime, this.orderItems)
  }

  private def updateState: (Event => Unit) = {
    case evt: CreateOrderEvt =>
      this.orderId = evt.orderId
      this.orderTime = evt.orderTime
      this.orderItems = evt.orderItems
    case evt: UpdateOrderEvt =>
      this.orderTime = evt.orderTime
      this.orderItems = evt.orderItems
    case evt: DeleteOrderEvt =>
    case evt: AddOrderItemsEvt =>
      this.orderItems ++= evt.orderItems
    case evt: RemoveOrderItemsEvt =>
      this.orderItems = this.orderItems.filter(x => !evt.orderItems.contains(x))
  }

}

