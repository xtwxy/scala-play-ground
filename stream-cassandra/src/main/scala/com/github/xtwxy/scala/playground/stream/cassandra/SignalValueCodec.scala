package com.github.xtwxy.scala.playground.stream.cassandra

import java.nio.ByteBuffer

import com.datastax.driver.core.{DataType, ProtocolVersion, TypeCodec}
import com.trueaccord.scalapb.TextFormat

object SignalValueCodec {
  def apply(): SignalValueCodec = new SignalValueCodec()
}

class SignalValueCodec extends TypeCodec[SignalValue](DataType.blob(), classOf[SignalValue]) {
  override def format(value: SignalValue): String = {
    TextFormat.printToString(value)
  }

  override def parse(value: String): SignalValue = {
    TextFormat.fromAscii[SignalValue](SignalValue, value).right.get
  }

  override def serialize(value: SignalValue, protocolVersion: ProtocolVersion): ByteBuffer = {
    ByteBuffer.wrap(value.toByteArray)
  }

  override def deserialize(bytes: ByteBuffer, protocolVersion: ProtocolVersion): SignalValue = {
    SignalValue.parseFrom(bytes.array())
  }
}
