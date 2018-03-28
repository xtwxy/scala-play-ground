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
      tcp_client,
      tcp_server,
      socketio_hello,
      stream_quickstart,
      stream_tweet,
      simple_xml,
      word_count
  )

lazy val jodatime          = (project in file("joda-time-pg"))
lazy val actor_mixed_java  = (project in file("actor-mixed-java"))
lazy val tcp_client        = (project in file("tcp-client"))
lazy val tcp_server        = (project in file("tcp-server"))
lazy val socketio_hello    = (project in file("socket.io-hello"))
  .enablePlugins(PlayScala)
lazy val stream_quickstart = (project in file("stream-quickstart"))
lazy val stream_tweet      = (project in file("stream-tweet"))
lazy val simple_xml        = (project in file("simple-xml"))
lazy val word_count        = (project in file("word-count"))

