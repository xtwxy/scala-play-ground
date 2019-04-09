/*****************************************************
 ** This file is 100% ***GENERATED***, DO NOT EDIT! **
 *****************************************************/
package com.apuex.sales.message

trait OrderCommand extends com.apuex.sales.message.ShardingEntityCommand {
  def orderId: String
  override def entityId: String = {
    s"order_${orderId}"
  }
}

