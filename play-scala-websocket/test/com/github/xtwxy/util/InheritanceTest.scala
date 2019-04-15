package com.github.xtwxy.util

import com.github.xtwxy.music.Child.Children._
import com.github.xtwxy.music._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object InheritanceTest extends App {
  val d1 = Derived1(Some("name1"))
  val d2 = Derived1(Some("name2"))
  implicit val derived1Format = Json.format[Derived1]
  implicit val derived2Format = Json.format[Derived2]

  Json.toJson(d1)
}
