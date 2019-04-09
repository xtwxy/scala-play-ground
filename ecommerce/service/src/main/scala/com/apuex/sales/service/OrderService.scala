/** ***************************************************
  * * This file is 100% ***GENERATED***, DO NOT EDIT! **
  * ****************************************************/
package com.apuex.sales.service

import akka.actor._
import com.github.apuex.springbootsolution.runtime._
import com.apuex.sales.message._
import javax.inject._

object OrderService {
  def props = Props[OrderService]

  def name: String = "OrderService"
}

class OrderService @Inject()(@Named("ShardingOrders") order: ActorRef)
  extends Actor with ActorLogging {

  override def receive: Receive = {
    case cmd: OrderCommand => order forward cmd
    case q: QueryCommand =>
    case x => log.info("unhandled COMMAND: {} {}", this, x)
  }
}

