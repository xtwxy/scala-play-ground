import Dependencies._
import sbtassembly.MergeStrategy

name         := "tcp-client"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

libraryDependencies ++= {
  Seq(
    akkaActor,
    scalaTest      % Test
  )
}

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.github.xtwxy.scala.playground.tcpclient.Main")
assemblyJarName in assembly := "tcp-client.jar"


