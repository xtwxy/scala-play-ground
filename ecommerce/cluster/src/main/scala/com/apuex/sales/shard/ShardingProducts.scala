/** ***************************************************
  * * This file is 100% ***GENERATED***, DO NOT EDIT! **
  * ****************************************************/
package com.apuex.sales.shard

import akka.actor._
import akka.cluster.sharding._
import com.apuex.sales.message._

object ShardingProducts {
  def props = Props[ShardingProducts]

  def name: String = "ShardingProducts"
}

class ShardingProducts extends Actor with ActorLogging {
  ShardingProduct.defaultNumberOfShards = 100

  def productShard(): ActorRef = {
    ClusterSharding(context.system).shardRegion(ShardingProduct.shardName)
  }

  ClusterSharding(context.system).start(
    ShardingProduct.shardName,
    ShardingProduct.props,
    ClusterShardingSettings(context.system),
    ShardingProduct.extractEntityId,
    ShardingProduct.extractShardId
  )

  override def receive: Receive = {
    case cmd: Command =>
      productShard() forward cmd
    case x => log.info("unhandled COMMAND: {} {}", this, x)
  }
}

