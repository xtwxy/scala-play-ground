import com.trueaccord.scalapb.compiler.Version.scalapbVersion
import Dependencies._

name         := "play-scala-websocket"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

// version := s"${System.getProperty("app.version", "1.0-SNAPSHOT")}"

libraryDependencies ++=
  Seq(
    groupName %% "musicdb" % artifactVersionNumber,
    scalaTestPlusPlay % Test
  )

publishTo := localRepo
