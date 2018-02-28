import Dependencies._

name         := "joda-time-pg"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

resolvers ++= dependedRepos

libraryDependencies ++= {
  Seq(
    jodaTime,
    scalaTest      % Test
  )
}

