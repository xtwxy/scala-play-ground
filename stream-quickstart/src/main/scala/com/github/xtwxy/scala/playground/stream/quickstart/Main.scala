package com.github.xtwxy.scala.playground.stream.quickstart


import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString
import akka.{Done, NotUsed}

import scala.collection.immutable.Seq
import scala.concurrent._

object Main extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()

  val source: Source[Int, NotUsed] = Source(Seq(1, 2, 3))
  val factorials = source.scan(BigInt(1))((acc, next) ⇒ acc * next)

  val result: Future[IOResult] =
    factorials
      .map(num ⇒ ByteString(s"$num\n"))
      .runWith(FileIO.toPath(Paths.get("factorials.txt")))

  val done: Future[Done] = source.runForeach(i ⇒ println(i))(materializer)

  implicit val ec = system.dispatcher
  done.onComplete(_ ⇒ system.terminate())

}
