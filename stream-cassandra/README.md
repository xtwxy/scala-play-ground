# Cassandra Connector Usage

## Connecting to Cluster

```
  val cluster: Cluster = Cluster
    .builder()
    .addContactPoint("127.0.0.1")
    .withPort(9042)
    .build()
```

## Creating Session

```
  implicit val session = cluster.connect()
```

## Integrating to Actor with `Source.queue`

```
  val source: Source[Integer, SourceQueueWithComplete[Integer]] = 
    Source.queue[Integer](1000, OverflowStrategy.dropNew)
```

## Using Cassandra Source

```
  val stmt = new SimpleStatement(s"SELECT * FROM $keyspaceName.test").setFetchSize(20)
  val source = CassandraSource(stmt)
  val result = source.toMat(Sink.foreach(x => println(x)))(Keep.right).run()
```

## Using Cassandra Sink

The following code shows an example of using Cassandra as output Sink of stream.
Using `Source.queue` to integrate with akka Actor as input source.

```
  val keyspaceName = "stream"
  val preparedStatement = session.prepare(s"INSERT INTO $keyspaceName.test(id) VALUES (?)")
  val statementBinder = (myInteger: Integer, statement: PreparedStatement) => statement.bind(myInteger)
  val sink = CassandraSink[Integer](2, preparedStatement, statementBinder)
  val (queue, result) = source.toMat(sink)(Keep.both).run()
  
  (0 until 10).foreach(x => queue.offer(x))
```

## Stream Completion

```
  queue.complete()
```

## Application Termination

To terminate application, thread pool/daemon threads must be terminated first,
or else the application refuse to terminate.

And, it's polite to close open sessions, if any.

```
  result.onComplete(_ => {
    session.close() // close open session.
    cluster.close() // required to terminate thread pool within the cluster.
    system.terminate()
  })
```

## Reference Links

- [Alpakka Connectors Cassandra Connector](https://developer.lightbend.com/docs/alpakka/latest/cassandra.html)