import Dependencies._
import sbtassembly.MergeStrategy

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.github.xtwxy.scala.playground",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "stream-quickstart",
    libraryDependencies += akkaStream,
    libraryDependencies += scalaTest % Test
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.github.xtwxy.scala.playground.stream.quickstart.Main")
assemblyJarName in assembly := "stream-quickstart.jar"