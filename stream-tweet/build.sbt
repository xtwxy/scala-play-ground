import Dependencies._
import sbtassembly.MergeStrategy

name         := "stream-tweet"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

libraryDependencies ++= {
  Seq(
    akkaStream,
    scalaTest      % Test
  )
}


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.github.xtwxy.scala.playground.stream.tweet.Main")
assemblyJarName in assembly := "stream-tweet.jar"


