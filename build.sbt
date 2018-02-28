import Dependencies._

name         := "scala-play-ground"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

resolvers ++= dependedRepos

lazy val root = (project in file("."))
  .aggregate(
      jodatime,
      socketio_hello,
      stream_quickstart,
      stream_tweet
  )

lazy val jodatime          = (project in file("joda-time-pg"))
lazy val socketio_hello    = (project in file("socket.io-hello"))
  .enablePlugins(PlayScala)
lazy val stream_quickstart = (project in file("stream-quickstart"))
lazy val stream_tweet      = (project in file("stream-tweet"))

