import Dependencies._

name         := "scala-play-ground"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

resolvers ++= dependedRepos

lazy val root = (project in file("."))
  .aggregate(
    jodatime,
    actor_mixed_java,
    cluster_event,
    read_journal_cassandra,
    multiple_persistence_plugin,
    read_journal_leveldb,
    tcp_client,
    tcp_server,
    socketio_hello,
    stream_actor,
    stream_cassandra,
    stream_queue,
    stream_quickstart,
    stream_tweet,
    simple_xml,
    word_count,
    play_scala_starter,
    play_java_seed,
    play_scala_seed
  )

lazy val jodatime          = (project in file("joda-time-pg"))
lazy val actor_mixed_java  = (project in file("actor-mixed-java"))
lazy val cluster_event  = (project in file("cluster-event"))
lazy val read_journal_cassandra  = (project in file("read-journal-cassandra"))
lazy val multiple_persistence_plugin = (project in file("multiple-persistence-plugin"))
lazy val read_journal_leveldb  = (project in file("read-journal-leveldb"))
lazy val tcp_client        = (project in file("tcp-client"))
lazy val tcp_server        = (project in file("tcp-server"))
lazy val socketio_hello    = (project in file("socket.io-hello"))
  .enablePlugins(PlayScala)
lazy val stream_actor      = (project in file("stream-actor"))
lazy val stream_cassandra  = (project in file("stream-cassandra"))
lazy val stream_queue      = (project in file("stream-queue"))
lazy val stream_quickstart = (project in file("stream-quickstart"))
lazy val stream_tweet      = (project in file("stream-tweet"))
lazy val simple_xml        = (project in file("simple-xml"))
lazy val word_count        = (project in file("word-count"))
//lazy val scala_native_hello= (project in file("scala-native-hello"))
lazy val play_scala_starter= (project in file("play-scala-starter-example"))
  .enablePlugins(PlayScala)

lazy val play_java_seed    = (project in file("play-java-seed"))
  .enablePlugins(PlayJava)
lazy val play_scala_seed   = (project in file("play-scala-seed"))
  .enablePlugins(PlayScala, PlayAkkaHttp2Support)

