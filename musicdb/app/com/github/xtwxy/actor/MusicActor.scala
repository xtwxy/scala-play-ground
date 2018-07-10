package com.github.xtwxy.actor

import javax.inject.Inject

import akka.actor.{Actor, ActorLogging}
import com.github.xtwxy.dao.MusicDAO
import play.api.libs.concurrent.InjectedActorSupport

class MusicActor @Inject() (musicDAO: MusicDAO) extends Actor with ActorLogging with InjectedActorSupport {
  override def receive = {
    case x =>
      sender() ! musicDAO.selectAllMusicTypes
      log.info(s"message: ${x.toString}")
  }
}
