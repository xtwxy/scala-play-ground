/*****************************************************
 ** This file is 100% ***GENERATED***, DO NOT EDIT! **
 *****************************************************/
package com.apuex.sales.json

import com.apuex.sales.message._
import play.api.libs.json._

trait OrderJsonSupport {
  implicit val byteStringFormat = ByteStringFormat.format
  implicit val timestampFormat = TimestampFormat.format
  implicit val queryCommandFormat = QueryCommandFormat.queryCommandFormat

  implicit val orderItemVoFormat = Json.format[OrderItemVo]
  implicit val orderVoFormat = Json.format[OrderVo]


  implicit val createOrderCmdFormat = Json.format[CreateOrderCmd]
  implicit val updateOrderCmdFormat = Json.format[UpdateOrderCmd]
  implicit val deleteOrderCmdFormat = Json.format[DeleteOrderCmd]
  implicit val retrieveOrderCmdFormat = Json.format[RetrieveOrderCmd]

  implicit val createOrderEvtFormat = Json.format[CreateOrderEvt]
  implicit val updateOrderEvtFormat = Json.format[UpdateOrderEvt]
  implicit val deleteOrderEvtFormat = Json.format[DeleteOrderEvt]

  implicit val addOrderItemsCmdFormat = Json.format[AddOrderItemsCmd]
  implicit val addOrderItemsEvtFormat = Json.format[AddOrderItemsEvt]
  implicit val removeOrderItemsCmdFormat = Json.format[RemoveOrderItemsCmd]
  implicit val removeOrderItemsEvtFormat = Json.format[RemoveOrderItemsEvt]

}

