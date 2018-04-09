package com.github.xtwxy.scala.playground.stream.cassandra


import akka.actor._
import akka.stream._
import akka.stream.alpakka.cassandra.scaladsl._
import akka.stream.scaladsl._
import com.datastax.driver.core.{Cluster, PreparedStatement}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {
  implicit val system = ActorSystem("Cassandra")
  implicit val dispatcher = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val cluster: Cluster = Cluster.builder
    .addContactPoint("127.0.0.1")
    .withPort(9042)
    .build()

  implicit val session = cluster.connect()

  val source: Source[Integer, SourceQueueWithComplete[Integer]] = Source.queue[Integer](1000, OverflowStrategy.dropNew)

  val keyspaceName = "stream"
  val preparedStatement = session.prepare(s"INSERT INTO $keyspaceName.test(id) VALUES (?)")
  val statementBinder = (myInteger: Integer, statement: PreparedStatement) => statement.bind(myInteger)
  val sink = CassandraSink[Integer](2, preparedStatement, statementBinder)
  val (queue, result) = source.toMat(sink)(Keep.both).run()

  (0 until 10).foreach(x => queue.offer(x))
  queue.complete()

  result.onComplete(_ ⇒ {
    session.close()
    cluster.close()
    system.terminate()
  })
}
