/*****************************************************
 ** This file is 100% ***GENERATED***, DO NOT EDIT! **
 *****************************************************/
package com.apuex.sales.router

import javax.inject._

import com.apuex.sales.controller._
import javax.inject.Inject

import play.api.mvc._
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class OrderRouter @Inject()(controller: OrderController)
  extends SimpleRouter {

  override def routes: Routes = {
    case POST(p"/create-order") => controller.create
    case POST(p"/retrieve-order") => controller.retrieve
    case POST(p"/update-order") => controller.update
    case POST(p"/delete-order") => controller.delete
    case POST(p"/query-order") => controller.query
    case POST(p"/add-order-items") => controller.addOrderItems
    case POST(p"/remove-order-items") => controller.removeOrderItems
  }
}

