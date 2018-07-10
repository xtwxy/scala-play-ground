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
    playAnorm,
    playSlick,
    mysqlDriver,
    webjarsPlay,
    webjarsJqueryI18n,
    webjarsEasyUI,
    scalapbRuntime % "protobuf",
    scalaTestPlusPlay % Test
  )

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

publishTo := localRepo
