import Dependencies._

name         := "scala-native-hello"
scalaVersion := "2.11.12"
organization := groupName
version      := artifactVersionNumber

enablePlugins(ScalaNativePlugin)

libraryDependencies ++=
  Seq(
    //scalaXml,
    "com.outr" %% "reactify" % "3.0.3"
  )

