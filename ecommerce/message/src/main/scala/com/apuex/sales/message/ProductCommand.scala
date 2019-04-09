/*****************************************************
 ** This file is 100% ***GENERATED***, DO NOT EDIT! **
 *****************************************************/
package com.apuex.sales.message

trait ProductCommand extends com.apuex.sales.message.ShardingEntityCommand {
  def productId: String
  override def entityId: String = {
    s"product_${productId}"
  }
}

