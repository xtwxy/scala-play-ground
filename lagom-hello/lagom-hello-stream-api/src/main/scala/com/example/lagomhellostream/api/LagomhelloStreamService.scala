package com.example.lagomhellostream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

/**
  * The lagom-hello stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the LagomhelloStream service.
  */
trait LagomhelloStreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor: Descriptor = {
    import Service._

    named("lagom-hello-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

