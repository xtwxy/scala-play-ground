package com.github.apuex.akka.gen.util

import com.github.xtwxy.scala.playground.journal.read.{JournalEvent, ProtobufSerializer}
import com.google.protobuf.timestamp.Timestamp
import org.scalatest.{FlatSpec, Matchers}

class ProtoBuffSpec extends FlatSpec with Matchers {

  "A Protobuf Serializer" should "serialize and deserialize scalapb message" in {
    val serializer = new ProtobufSerializer();
    val journalEvent = JournalEvent("", Some(Timestamp(0, 0)), 0)
    serializer.fromBinary(serializer.toBinary(journalEvent), Some(classOf[JournalEvent])) should be(journalEvent)
  }
}
