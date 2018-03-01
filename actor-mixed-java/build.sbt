import Dependencies._

name         := "actor-mixed-java"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

resolvers ++= dependedRepos

libraryDependencies ++= {
  Seq(
    akkaActor,
    scalaTest      % Test
  )
}

