import Dependencies._
import sbtassembly.MergeStrategy

name         := "cluster-event"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

libraryDependencies ++= {
  Seq(
    akkaCluster,
    scalaTest      % Test
  )
}


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.github.xtwxy.scala.playground.clusterevent.Main")
assemblyJarName in assembly := "cluster-event.jar"

