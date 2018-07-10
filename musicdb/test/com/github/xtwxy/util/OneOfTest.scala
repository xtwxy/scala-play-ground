package com.github.xtwxy.util

import com.github.xtwxy.music.Child.Children._
import com.github.xtwxy.music._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object OneOfTest extends App {
  val firstChild: FirstChild = FirstChild("bastard")
  val secondChild: SecondChild = SecondChild("another bastard")

  val child1: Child = Child("0001", Bob(firstChild))
  val child2: Child = Child("0002", Alice(secondChild))
  val child3: Child = Child("0003").withBob(firstChild).withAlice(secondChild)

  val firstChildFormat = Json.format[FirstChild]
  val secondChildFormat = Json.format[SecondChild]
  val childChildReads: Reads[Child.Children] = new Reads[Child.Children] {
    override def reads(json: JsValue): JsResult[Child.Children] = {
      val bob = (json \ "bob").asOpt[FirstChild](firstChildFormat)
      val alice = (json \ "alice").asOpt[SecondChild](secondChildFormat)

      if (bob.isDefined) {
          JsSuccess(Bob(bob.get))
      } else if (alice.isDefined) {
        JsSuccess(Alice(alice.get))
      } else {
        JsSuccess(Empty)
      }
    }
  }

  val childChildWrites: Writes[Child.Children] = new Writes[Child.Children] {
    override def writes(o: Child.Children): JsValue = o match {
      case x: Bob => JsObject(Seq("bob" -> firstChildFormat.writes(x.value)))
      case x: Alice => JsObject(Seq("alice" -> secondChildFormat.writes(x.value)))
      case _ => JsNull
    }
  }

  implicit val childWrites: Writes[Child] = (
    (JsPath \ "id").write[String] and
      (JsPath \"children").lazyWrite(childChildWrites)
  )(unlift(Child.unapply))

  implicit val childReads: Reads[Child] = (
    (JsPath \ "id").read[String] and
      (JsPath \"children").lazyRead(childChildReads)
    )(Child.apply _)

  val json = Json.toJson(Seq(child1, child2, child3))
  println(json)
  println(Json.fromJson(json))
  val json1 = Json.toJson(child1)
  Json.fromJson(json1)

}
