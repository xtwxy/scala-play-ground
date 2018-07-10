package com.github.xtwxy.json

import com.github.xtwxy.music._
import play.api.libs.json.Json

trait TreeJsonSupport {
  implicit val treeFormat = Json.format[TreeVo]
}
