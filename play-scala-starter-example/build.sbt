import Dependencies._

name         := "play-scala-starter-example"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += guice
libraryDependencies += scalaTestPlusPlay % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
