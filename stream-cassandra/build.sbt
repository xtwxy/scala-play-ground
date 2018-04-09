import Dependencies._
import sbtassembly.MergeStrategy

name         := "stream-cassandra"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

libraryDependencies ++= {
  Seq(
    akkaStream,
    akkaStreamCassandra,
    slf4jApi,
    slf4jSimple,
    scalaTest      % Test
  )
}


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.github.xtwxy.scala.playground.stream.cassandra.Main")
assemblyJarName in assembly := "stream-cassandra.jar"

