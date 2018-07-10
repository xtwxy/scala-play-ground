package com.github.xtwxy.dao

import javax.inject.{Inject, Singleton}

import anorm.{SQL, SqlQuery}
import com.github.xtwxy.music.MusicVo
import com.github.xtwxy.util.MusicResultSetParser
import play.api.db.{Database, NamedDatabase}

@Singleton class MusicDAO @Inject()(@NamedDatabase("music") database: Database) {
  /**
    * Another approach: inject var.
   */
  @Inject()
  @NamedDatabase("music")
  var db: Database = null
  val sql: SqlQuery = SQL("SELECT music_id, music_type, music_name, music_composer from music")

  def selectAllMusicTypes: List[MusicVo] = db.withTransaction { implicit c =>
    sql.as(MusicResultSetParser.music.*)
  }
}
