name := """play-protobuf-gson"""
organization := "com.github.apuex.play.protobuf.gson"
maintainer := "xtwxy@hotmail.com"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "com.github.apuex.springbootsolution" %% "runtime" % "1.0.7"
libraryDependencies += "com.github.apuex.play" %% "body-parser" % "1.0.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test

