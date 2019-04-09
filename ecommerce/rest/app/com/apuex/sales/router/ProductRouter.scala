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

class ProductRouter @Inject()(controller: ProductController)
  extends SimpleRouter {

  override def routes: Routes = {
    case POST(p"/create-product") => controller.create
    case POST(p"/retrieve-product") => controller.retrieve
    case POST(p"/update-product") => controller.update
    case POST(p"/delete-product") => controller.delete
    case POST(p"/query-product") => controller.query
    case POST(p"/change-product-name") => controller.changeProductName
    case POST(p"/change-product-unit") => controller.changeProductUnit
    case POST(p"/change-unit-price") => controller.changeUnitPrice
    case POST(p"/update-product-sales") => controller.updateProductSales
    case POST(p"/get-product-sales") => controller.getProductSales
  }
}

