package com.github.xtwxy.scala.playground.stream.wordcount


import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString
import akka.{Done, NotUsed}

import scala.collection.immutable.Seq
import scala.concurrent._

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._


object Main extends App {
  val MaximumDistinctWords = 1000
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()

  val words = Source(args.toList)
  val counts: Source[(String, Int), NotUsed] = words
    // split the words into separate streams first
    .groupBy(MaximumDistinctWords, identity)
    //transform each element to pair with number of words in it
    .map(_ -> 1)
    // add counting logic to the streams
    .reduce((l, r) ⇒ (l._1, l._2 + r._2))
    // get a stream of word counts
    .mergeSubstreams

  val done = counts.runForeach(x => println(s"${x._1} => ${x._2}"))

  implicit val ec = system.dispatcher
  done.onComplete(_ ⇒ system.terminate())
}
