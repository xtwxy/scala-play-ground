package com.github.xtwxy.util

import com.github.xtwxy.dao.MusicDAO
import com.github.xtwxy.json.MusicJsonSupport
import com.github.xtwxy.music._
import com.typesafe.config.ConfigFactory
import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json._

object Main extends App with MusicJsonSupport {
  val config = ConfigFactory.load("application.conf")
  val app = new GuiceApplicationBuilder().configure(Configuration(config)).build()
  val musicDAO = app.injector.instanceOf[MusicDAO]

  val result: List[MusicVo] = musicDAO.selectAllMusicTypes
  println(s"${Json.toJson(result)}")
}
