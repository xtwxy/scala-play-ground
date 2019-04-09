/** ***************************************************
  * * This file is 100% ***GENERATED***, DO NOT EDIT! **
  * ****************************************************/
package com.apuex.sales.shard

import akka.actor._
import akka.cluster.sharding._
import com.apuex.sales.domain._
import com.apuex.sales.message._

import scala.math.Numeric.IntIsIntegral._

object ShardingProduct {
  def props = Props[ShardingProduct]

  def name(productId: String): String = productId.toString

  val shardName: String = "ShardingProduct"
  var defaultNumberOfShards = 100

  val extractEntityId: ShardRegion.ExtractEntityId = {
    case cmd: ShardingEntityCommand =>
      (cmd.entityId.toString, cmd)
  }

  val extractShardId: ShardRegion.ExtractShardId = {
    case cmd: ShardingEntityCommand =>
      (abs(cmd.entityId.hashCode) % defaultNumberOfShards).toString
    case ShardRegion.StartEntity(id) =>
      // StartEntity is used by remembering entities feature
      (abs(id.hashCode) % defaultNumberOfShards).toString
  }
}

class ShardingProduct extends Product {
  override def unhandled(message: Any): Unit = message match {
    case x => log.info("unhandled COMMAND: {} {}", this, x)
  }
}

