package com.apuex.sales.json

import com.github.apuex.springbootsolution.runtime._
import com.google.protobuf.util._
import play.api.libs.json._

object QueryCommandFormat {
  implicit val queryCommandFormat = new QueryCommandFormat
}

class QueryCommandFormat extends OFormat[QueryCommand] {


  val registry: JsonFormat.TypeRegistry = JsonFormat.TypeRegistry.newBuilder
    .add(Messages.getDescriptor.getMessageTypes)
    .build
  val printer: JsonFormat.Printer = JsonFormat.printer.usingTypeRegistry(registry)
  val parser: JsonFormat.Parser = JsonFormat.parser.usingTypeRegistry(registry)

  override def writes(o: QueryCommand): JsObject = {
    Json.parse(
      printer.print(o)
    ).validate[JsObject].get
  }

  override def reads(json: JsValue): JsResult[QueryCommand] = {
    val builder = QueryCommand.newBuilder()
    parser.merge(json.toString(), builder)
    JsSuccess(builder.build())
  }
}