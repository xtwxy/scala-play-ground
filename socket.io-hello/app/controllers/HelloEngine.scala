package controllers

import akka.NotUsed
import akka.stream.Materializer
import akka.stream.scaladsl.{BroadcastHub, Flow, Keep, MergeHub}
import play.socketio.scaladsl.SocketIO
import play.socketio.scaladsl.SocketIOEventCodec._

class HelloEngine(socketIO: SocketIO)(implicit mat: Materializer) {

  val decoder = decodeByName {
    case "foo" => decodeJson[String]
  }

  val encoder = encodeByType[String] {
    case _: String => "foo" -> encodeJson[String]
  }

  val chatFlow: Flow[String, String, NotUsed] = {
    val (sink, source) = MergeHub.source[String].toMat(BroadcastHub.sink)(Keep.both).run()
    Flow.fromSinkAndSourceCoupled(sink, source)
  }

  val controller = {
    socketIO.builder
      .addNamespace("/foo", decoder, encoder, chatFlow)
      .createController()
  }
}
