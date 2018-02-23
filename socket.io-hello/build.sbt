name := """socket.io-hello"""
organization := "com.github.xtwxy.scala.playground"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.lightbend.play" %% "play-socket-io" % "1.0.0-beta-2"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.github.xtwxy.scala.playground.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.github.xtwxy.scala.playground.binders._"
