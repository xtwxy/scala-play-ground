package com.apuex.sales.json

import com.google.protobuf.timestamp._
import play.api.libs.json._
import com.apuex.sales.util.DateFormat._

object TimestampFormat {
  def format: Format[Timestamp] = new Format[Timestamp] {
    override def writes(o: Timestamp) = JsString(formatTimestamp(o))

    override def reads(json: JsValue) = {
      try {
        json match {
          case JsString(str) =>
            JsSuccess(parseProtobufTimestamp(str))
          case _ => JsError(json.toString())
        }
      } catch {
        case e: Exception => JsError(e.toString)
      }
    }
  }
}
