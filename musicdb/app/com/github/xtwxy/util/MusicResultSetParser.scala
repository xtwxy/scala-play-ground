package com.github.xtwxy.util

import anorm.SqlParser.get
import anorm.{RowParser, ~}
import com.github.xtwxy.music._;

object MusicResultSetParser {
  val music: RowParser[MusicVo] = {
    get[Long]("music_id") ~
      get[String]("music_name") ~
      get[String]("music_composer") ~
      get[Int]("music_type") map {
      case id ~ name ~ composer ~ musicType => MusicVo.apply(id, Some(name), Some(composer), Some(MusicType.fromValue(musicType)))
    }
  }
}
