package com.example.lagomhellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.lagomhellostream.api.LagomhelloStreamService
import com.example.lagomhello.api.LagomhelloService

import scala.concurrent.Future

/**
  * Implementation of the LagomhelloStreamService.
  */
class LagomhelloStreamServiceImpl(lagomhelloService: LagomhelloService) extends LagomhelloStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(lagomhelloService.hello(_).invoke()))
  }
}
