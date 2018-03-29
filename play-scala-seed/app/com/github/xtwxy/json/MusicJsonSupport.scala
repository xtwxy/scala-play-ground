package com.github.xtwxy.json

import com.github.xtwxy.music.{MusicType, MusicVo}
import com.github.xtwxy.util.EnumFormat
import play.api.libs.json.Json

trait MusicJsonSupport {
  implicit val musicTypeFormat = EnumFormat.format(MusicType)
  implicit val musicFormat = Json.format[MusicVo]
}
