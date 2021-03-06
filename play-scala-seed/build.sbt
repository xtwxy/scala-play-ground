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
    groupName %% "musicdb" % artifactVersionNumber,
    scalaTestPlusPlay % Test
  )

publishTo := localRepo
