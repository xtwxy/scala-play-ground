package com.github.xtwxy.scala.playground.clusterevent

import akka.actor._
import akka.cluster.ClusterEvent._
import akka.cluster._

class ClusterEventListenerActor extends Actor with ActorLogging {


  val cluster = Cluster(context.system)

  // subscribe to cluster changes, re-subscribe when restart
  override def preStart(): Unit = {
    cluster.subscribe(self, initialStateMode = InitialStateAsEvents,
      classOf[MemberEvent], classOf[UnreachableMember])
  }
  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive = {
    case MemberUp(member) ⇒
      log.info("Member is Up: {}", member.address)
    case UnreachableMember(member) ⇒
      log.info("Member detected as unreachable: {}", member)
    case MemberRemoved(member, previousStatus) ⇒
      log.info(
        "Member is Removed: {} after {}",
        member.address, previousStatus)
    case x: MemberEvent ⇒ log.info(s"Other Event: ${x}")// ignore
  }
}
