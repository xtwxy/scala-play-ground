/*****************************************************
 ** This file is 100% ***GENERATED***, DO NOT EDIT! **
 *****************************************************/
package com.apuex.sales.json

import com.apuex.sales.message._
import play.api.libs.json._

trait ProductJsonSupport {
  implicit val byteStringFormat = ByteStringFormat.format
  implicit val timestampFormat = TimestampFormat.format
  implicit val queryCommandFormat = QueryCommandFormat.queryCommandFormat

  implicit val productVoFormat = Json.format[ProductVo]


  implicit val createProductCmdFormat = Json.format[CreateProductCmd]
  implicit val updateProductCmdFormat = Json.format[UpdateProductCmd]
  implicit val deleteProductCmdFormat = Json.format[DeleteProductCmd]
  implicit val retrieveProductCmdFormat = Json.format[RetrieveProductCmd]

  implicit val createProductEvtFormat = Json.format[CreateProductEvt]
  implicit val updateProductEvtFormat = Json.format[UpdateProductEvt]
  implicit val deleteProductEvtFormat = Json.format[DeleteProductEvt]

  implicit val changeProductNameCmdFormat = Json.format[ChangeProductNameCmd]
  implicit val changeProductNameEvtFormat = Json.format[ChangeProductNameEvt]

  implicit val changeProductUnitCmdFormat = Json.format[ChangeProductUnitCmd]
  implicit val changeProductUnitEvtFormat = Json.format[ChangeProductUnitEvt]

  implicit val changeUnitPriceCmdFormat = Json.format[ChangeUnitPriceCmd]
  implicit val changeUnitPriceEvtFormat = Json.format[ChangeUnitPriceEvt]

  implicit val updateProductSalesCmdFormat = Json.format[UpdateProductSalesCmd]
  implicit val productSalesVoFormat = Json.format[ProductSalesVo]
  implicit val getProductSalesCmdFormat = Json.format[GetProductSalesCmd]

}

