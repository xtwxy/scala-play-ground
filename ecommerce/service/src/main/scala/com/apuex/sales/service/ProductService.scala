/** ***************************************************
  * * This file is 100% ***GENERATED***, DO NOT EDIT! **
  * ****************************************************/
package com.apuex.sales.service

import akka.actor._
import com.apuex.sales.message._
import com.github.apuex.springbootsolution.runtime._
import javax.inject._

object ProductService {
  def props = Props[ProductService]

  def name: String = "ProductService"
}

class ProductService @Inject()(@Named("ShardingProducts") product: ActorRef)
  extends Actor with ActorLogging {

  override def receive: Receive = {
    case cmd: ProductCommand => product forward cmd
    case q: QueryCommand =>
    case x => log.info("unhandled COMMAND: {} {}", this, x)
  }
}

