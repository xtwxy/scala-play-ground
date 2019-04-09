/** ***************************************************
  * * This file is 100% ***GENERATED***, DO NOT EDIT! **
  * ****************************************************/
package com.apuex.sales.shard

import akka.actor._
import akka.cluster.sharding._
import com.apuex.sales.message._

object ShardingOrders {
  def props = Props[ShardingOrders]

  def name: String = "ShardingOrders"
}

class ShardingOrders
  extends Actor with ActorLogging {
  ShardingOrder.defaultNumberOfShards = 100

  def orderShard(): ActorRef = {
    ClusterSharding(context.system).shardRegion(ShardingOrder.shardName)
  }

  ClusterSharding(context.system).start(
    ShardingOrder.shardName,
    ShardingOrder.props,
    ClusterShardingSettings(context.system),
    ShardingOrder.extractEntityId,
    ShardingOrder.extractShardId
  )

  override def receive: Receive = {
    case cmd: Command =>
      orderShard() forward cmd
    case x => log.info("unhandled COMMAND: {} {}", this, x)
  }
}

