package com.github.xtwxy.dao

import javax.inject.{Inject, Singleton}

import anorm.SqlParser.get
import anorm.{RowParser, SQL, SqlQuery, ~}
import com.github.xtwxy.music._
import play.api.db.{Database, NamedDatabase}

object TreeDAO {
  val tree: RowParser[TreeVo] = {
    get[Long]("id") ~
      get[Long]("parentId") ~
      get[String]("name") map {
      case id ~ parentId ~ name => TreeVo(Some(id), Some(parentId), Some(name))
    }
  }
}

@Singleton class TreeDAO @Inject()(@NamedDatabase("music") database: Database) {
  def selectTreeNodesByParentId(parentId: Long): List[TreeVo] = database.withTransaction { implicit c =>
    SQL("SELECT id, parentId, name from tree where parentId = {parentId}")
      .on("parentId" -> parentId)
      .as(TreeDAO.tree.*)
  }
}
