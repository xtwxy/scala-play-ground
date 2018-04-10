package com.github.xtwxy.scala.playground.stream.cassandra

import java.nio.ByteBuffer

import com.datastax.driver.core.{DataType, ProtocolVersion, TypeCodec}
import com.trueaccord.scalapb.TextFormat

object SignalValueCodec {
  def apply(): SignalValueCodec = new SignalValueCodec()
}

/**
  * Codec for SignalValue, Not for use with cqlsh.
  */
class SignalValueCodec extends TypeCodec[SignalValue](DataType.blob(), classOf[SignalValue]) {
  /**
    * DO NOT USE THIS METHOD!
    * FIXME: This method is not properly implemented.
    *
    * @param value
    * @return
    */
  override def format(value: SignalValue): String = {
    TextFormat.printToString(value)
  }

  /**
    * DO NOT USE THIS METHOD!
    * FIXME: This method is not properly implemented.
    *
    * @param value
    * @return
    */
  override def parse(value: String): SignalValue = {
    TextFormat.fromAscii[SignalValue](SignalValue, value).right.get
  }

  /**
    * Serialize SignalValue to ByteBuffer.
    *
    * @param value
    * @param protocolVersion
    * @return
    */
  override def serialize(value: SignalValue, protocolVersion: ProtocolVersion): ByteBuffer = {
    ByteBuffer.wrap(value.toByteArray)
  }

  /**
    * Deserialize SignalValue from ByteBuffer.
    *
    * @param bytes
    * @param protocolVersion
    * @return
    */
  override def deserialize(bytes: ByteBuffer, protocolVersion: ProtocolVersion): SignalValue = {
    SignalValue.parseFrom(bytes.array())
  }
}
