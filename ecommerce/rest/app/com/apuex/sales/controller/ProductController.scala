/*****************************************************
 ** This file is 100% ***GENERATED***, DO NOT EDIT! **
 *****************************************************/
package com.apuex.sales.controller

import javax.inject._

import akka.actor._
import akka.pattern._
import akka.util._
import com.github.apuex.springbootsolution.runtime._
import com.apuex.sales.json._
import com.apuex.sales.message._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent._
import scala.concurrent.duration._

@Singleton
class ProductController @Inject()(cc: ControllerComponents, @Named("ProductService") product: ActorRef)(implicit ec: ExecutionContext)
  extends AbstractController(cc)
    with ProductJsonSupport {
  implicit val timeout: Timeout = FiniteDuration(20, SECONDS)

  /**
    * curl -H 'Content-Type: application/json' -X POST -d '{"productId":"123456","productName":"iPhone","productUnit":"ton","unitPrice":1000.0}' http://localhost:9000/api/product/create-product
    * @return
    */
  def create = Action.async(parse.json) { request =>
    val result = request.body.validate[CreateProductCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case _ => Created
        }
      }
    )
  }

  /**
    * curl -H 'Content-Type: application/json' -X POST -d '{"productId":"123456","productName":"iPhone","productUnit":"ton","unitPrice":1000.0}' http://localhost:9000/api/product/retrieve-product
    * @return
    */
  def retrieve = Action.async(parse.json) { request =>
    val result = request.body.validate[RetrieveProductCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case r: ProductVo => Ok(Json.toJson(r))
        }
      }
    )
  }

  def update = Action.async(parse.json) { request =>
    val result = request.body.validate[UpdateProductCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }

  def query = Action.async(parse.json) { request =>
    val result = request.body.validate[QueryCommand]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case ProductListVo(list) => Ok(Json.toJson(list))
        }
      }
    )
  }

  def delete = Action.async(parse.json) { request =>
    val result = request.body.validate[DeleteProductCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }

  def changeProductName = Action.async(parse.json) { request =>
    val result = request.body.validate[ChangeProductNameCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }

  def changeProductUnit = Action.async(parse.json) { request =>
    val result = request.body.validate[ChangeProductUnitCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }

  def changeUnitPrice = Action.async(parse.json) { request =>
    val result = request.body.validate[ChangeUnitPriceCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }

  def updateProductSales = Action.async(parse.json) { request =>
    val result = request.body.validate[UpdateProductSalesCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }

  def getProductSales = Action.async(parse.json) { request =>
    val result = request.body.validate[GetProductSalesCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        product.ask(x).mapTo[ValueObject].map {
          case r: ProductSalesVo => Ok(Json.toJson(r))
        }
      }
    )
  }
}

