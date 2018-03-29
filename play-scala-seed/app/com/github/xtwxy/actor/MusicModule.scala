package com.github.xtwxy.actor

import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class MusicModule extends AbstractModule with AkkaGuiceSupport {
  override def configure() = {
    bindActor[MusicActor]("music-actor")
  }
}
