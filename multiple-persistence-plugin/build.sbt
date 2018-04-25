import Dependencies._
import sbtassembly.MergeStrategy

name         := "multiple-persistence-plugin"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

libraryDependencies ++= {
  Seq(
    akkaCluster,
    akkaClusterTools,
    akkaPersistence,
    akkaPersistenceQuery,
    akkaPersistenceCassandra,
    akkaPersistenceCassandraLauncher,
    scalapbCompiler,
    scalapbRuntime % "protobuf",
    slf4jApi,
    slf4jSimple,
    scalaTest      % Test
  )
}

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.github.xtwxy.scala.playground.journal.multiple.Main")
assemblyJarName in assembly := s"${name.value}.jar"

