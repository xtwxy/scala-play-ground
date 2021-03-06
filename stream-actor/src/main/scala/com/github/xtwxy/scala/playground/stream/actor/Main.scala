package com.github.xtwxy.scala.playground.stream.actor


import java.nio.file.Paths

import akka.actor._
import akka.actor.Status._
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.collection.immutable.Seq

object Main extends App {
  implicit val system = ActorSystem("Actor")
  implicit val materializer = ActorMaterializer()

  val source: Source[Any, ActorRef] = Source.actorRef[Any](1000, OverflowStrategy.dropNew)
  val factorials = source.scan[Any](BigInt(1))((acc, next) ⇒ next match {
    case x: Int => x * acc.asInstanceOf[BigInt]
  })

  val (actor, result) =
    factorials
      .map(num ⇒ ByteString(s"$num\n"))
      .toMat(FileIO.toPath(Paths.get("factorials.txt")))(Keep.both).run()

  Seq[Any](1, 2, 3, Success).foreach(x => actor ! x)

  implicit val ec = system.dispatcher
  result.onComplete(_ ⇒ system.terminate())

}
