package com.github.xtwxy.scala.playground.stream.cassandra


import akka.actor._
import akka.stream._
import akka.stream.alpakka.cassandra.scaladsl._
import akka.stream.scaladsl._
import com.datastax.driver.core.{Cluster, PreparedStatement}
import com.github.xtwxy.`scala`.playground.stream.cassandra.SignalType._
import com.google.protobuf.timestamp.Timestamp

object Main extends App {
  implicit val system = ActorSystem("Cassandra")
  implicit val dispatcher = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val cluster: Cluster = Cluster
    .builder()
    .addContactPoint("127.0.0.1")
    .withPort(9042)
    .build()

  cluster
    .getConfiguration()
    .getCodecRegistry()
    .register(SignalValueCodec())

  implicit val session = cluster.connect()

  val source: Source[SignalValueLog, SourceQueueWithComplete[SignalValueLog]] = Source.queue[SignalValueLog](1000, OverflowStrategy.dropNew)

  val keyspaceName = "stream"
  val preparedStatement = session.prepare(s"INSERT INTO $keyspaceName.signal_log(id, ts, value) VALUES (?, ?, ?)")
  val statementBinder = (log: SignalValueLog, statement: PreparedStatement) => statement.bind(log.id, log.ts, log.value)
  val sink = CassandraSink[SignalValueLog](2, preparedStatement, statementBinder)
  val (queue, result) = source.toMat(sink)(Keep.both).run()

  Seq(
    SignalValueLog("1", Some(Timestamp(System.currentTimeMillis()/1000, 0)), Some(SignalValue(AI, 12.3, false, ""))),
    SignalValueLog("2", Some(Timestamp(System.currentTimeMillis()/1000, 0)), Some(SignalValue(DI, 0, true, ""))),
    SignalValueLog("3", Some(Timestamp(System.currentTimeMillis()/1000, 0)), Some(SignalValue(SI, 0, false, "hello"))),
  ).foreach(x => queue.offer(x))

  queue.complete()

  result.onComplete(_ ⇒ {
    session.close()
    cluster.close()
    system.terminate()
  })
}
