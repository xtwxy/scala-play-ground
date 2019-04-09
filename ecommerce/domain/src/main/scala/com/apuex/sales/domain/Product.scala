/** ***************************************************
  * * This file is 100% ***GENERATED***, DO NOT EDIT! **
  * ****************************************************/
package com.apuex.sales.domain

import akka.actor._
import akka.persistence._
import akka.util.Timeout._
import akka.util._
import com.apuex.sales.message._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object Product {
  def props = Props[Product]
}

class Product extends PersistentActor with ActorLogging {
  override def persistenceId: String = s"${self.path.name}"

  implicit def requestTimeout: Timeout = FiniteDuration(20, SECONDS)

  implicit def executionContext: ExecutionContext = context.dispatcher

  var productId: String = null
  var productName: String = null
  var productUnit: String = null
  var unitPrice: Double = 0.0
  var recordTime: Option[Double] = None
  var quantitySold: Option[Double] = None

  override def receiveRecover: Receive = {
    case evt: Event =>
      updateState(evt)
    case SnapshotOffer(_, x: ProductVo) =>
      this.productId = x.productId
      this.productName = x.productName
      this.productUnit = x.productUnit
      this.unitPrice = x.unitPrice
    case x: RecoveryCompleted => log.info("RECOVER Completed: {} {}", this, x)
    case x => log.info("RECOVER Discarded: {} {}", this, x)
  }

  override def receiveCommand: Receive = {
    case cmd: CreateProductCmd =>
      persist(CreateProductEvt(cmd.userId, cmd.productId, cmd.productName, cmd.productUnit, cmd.unitPrice))(updateState)
      sender() ! ProductVo(this.productId, this.productName, this.productUnit, this.unitPrice)
    case cmd: UpdateProductCmd =>
      persist(UpdateProductEvt(cmd.userId, cmd.productId, cmd.productName, cmd.productUnit, cmd.unitPrice))(updateState)
      sender() ! ProductVo(this.productId, this.productName, this.productUnit, this.unitPrice)
    case cmd: DeleteProductCmd =>
      persist(DeleteProductEvt(cmd.userId, cmd.productId))(updateState)
      sender() ! ProductVo(this.productId, this.productName, this.productUnit, this.unitPrice)
    case _: RetrieveProductCmd =>
      sender() ! ProductVo(this.productId, this.productName, this.productUnit, this.unitPrice)
    case cmd: ChangeProductNameCmd =>
      persist(ChangeProductNameEvt(cmd.userId, cmd.productId, cmd.productName))(updateState)
      sender() ! ProductVo(this.productId, this.productName, this.productUnit, this.unitPrice)
    case cmd: ChangeProductUnitCmd =>
      persist(ChangeProductUnitEvt(cmd.userId, cmd.productId, cmd.productUnit))(updateState)
      sender() ! ProductVo(this.productId, this.productName, this.productUnit, this.unitPrice)
    case cmd: ChangeUnitPriceCmd =>
      persist(ChangeUnitPriceEvt(cmd.userId, cmd.productId, cmd.unitPrice))(updateState)
      sender() ! ProductVo(this.productId, this.productName, this.productUnit, this.unitPrice)
    case cmd: UpdateProductSalesCmd =>
      this.productName = cmd.productName
      this.recordTime = cmd.recordTime
      this.quantitySold = cmd.quantitySold
    case cmd: GetProductSalesCmd =>
      sender() ! ProductSalesVo(cmd.productId, this.productName, this.recordTime, this.quantitySold)
  }

  private def updateState: (Event => Unit) = {
    case evt: CreateProductEvt =>
      this.productId = evt.productId
      this.productName = evt.productName
      this.productUnit = evt.productUnit
      this.unitPrice = evt.unitPrice
    case evt: UpdateProductEvt =>
      this.productName = evt.productName
      this.productUnit = evt.productUnit
      this.unitPrice = evt.unitPrice
    case evt: DeleteProductEvt =>
    case evt: ChangeProductNameEvt =>
      this.productName = evt.productName
    case evt: ChangeProductUnitEvt =>
      this.productUnit = evt.productUnit
    case evt: ChangeUnitPriceEvt =>
      this.unitPrice = evt.unitPrice
  }
}

