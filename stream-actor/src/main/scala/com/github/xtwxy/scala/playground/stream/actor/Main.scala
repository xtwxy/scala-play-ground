package com.github.xtwxy.scala.playground.stream.actor


import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.collection.immutable.Seq

object Main extends App {
  implicit val system = ActorSystem("Actor")
  implicit val materializer = ActorMaterializer()

  val source: Source[Int, SourceQueueWithComplete[Int]] = Source.queue[Int](1000, OverflowStrategy.dropNew)
  val factorials = source.scan(BigInt(1))((acc, next) ⇒ acc * next)

  val (queue, result) =
    factorials
      .map(num ⇒ ByteString(s"$num\n"))
      .toMat(FileIO.toPath(Paths.get("factorials.txt")))(Keep.both).run()

  Seq(1, 2, 3).foreach(x => queue.offer(x))
  queue.complete()

  implicit val ec = system.dispatcher
  result.onComplete(_ ⇒ system.terminate())

}
