/** ***************************************************
  * * This file is 100% ***GENERATED***, DO NOT EDIT! **
  * ****************************************************/
package com.apuex.sales.controller

import akka.actor._
import akka.pattern._
import akka.util._
import com.apuex.sales.json._
import com.apuex.sales.message._
import com.github.apuex.springbootsolution.runtime._
import javax.inject._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent._
import scala.concurrent.duration._

@Singleton
class OrderController @Inject()(cc: ControllerComponents, @Named("OrderService") order: ActorRef)(implicit ec: ExecutionContext)
  extends AbstractController(cc)
    with OrderJsonSupport {
  implicit val timeout: Timeout = FiniteDuration(20, SECONDS)

  def create = Action.async(parse.json) { request =>
    val result = request.body.validate[CreateOrderCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        order.ask(x).mapTo[ValueObject].map {
          case _ => Created
        }
      }
    )
  }

  def retrieve = Action.async(parse.json) { request =>
    val result = request.body.validate[RetrieveOrderCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        order.ask(x).mapTo[ValueObject].map {
          case r: OrderVo => Ok(Json.toJson(r))
          case _ => NotFound
        }
      }
    )
  }

  def update = Action.async(parse.json) { request =>
    val result = request.body.validate[UpdateOrderCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        order.ask(x).mapTo[ValueObject].map {
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
        order.ask(x).mapTo[ValueObject].map {
          case OrderListVo(list) => Ok(Json.toJson(list))
          case _ => NotFound
        }
      }
    )
  }

  def delete = Action.async(parse.json) { request =>
    val result = request.body.validate[DeleteOrderCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        order.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }

  def addOrderItems = Action.async(parse.json) { request =>
    val result = request.body.validate[AddOrderItemsCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        order.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }

  def removeOrderItems = Action.async(parse.json) { request =>
    val result = request.body.validate[RemoveOrderItemsCmd]
    result.fold(
      errors => Future({
        BadRequest
      }),
      x => {
        order.ask(x).mapTo[ValueObject].map {
          case _ => Ok
        }
      }
    )
  }
}

