import com.trueaccord.scalapb.compiler.Version.scalapbVersion
import Dependencies._

name         := "play-scala-seed"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

// version := s"${System.getProperty("app.version", "1.0-SNAPSHOT")}"

scalaVersion := "2.12.3"

libraryDependencies ++=
  Seq(
    guice,
    jdbc,
    "com.typesafe.play" %% "anorm" % "2.5.3",
    "com.typesafe.play" %% "play-slick" % "3.0.2",
    "mysql" % "mysql-connector-java" % "6.0.6",
    "com.trueaccord.scalapb" %% "scalapb-runtime" % scalapbVersion % "protobuf",
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.discard
  case PathList("reference.conf") => MergeStrategy.concat
  case PathList("play", "reference-overrides.conf") => MergeStrategy.concat
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

mainClass in assembly := Some("play.core.server.ProdServerStart")
assemblyJarName in assembly := "play-scala-seed.jar"
