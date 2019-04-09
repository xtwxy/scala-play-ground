import Dependencies.{googleGuice, _}

name := "domain"
scalaVersion := scalaVersionNumber
organization := ecommerceGroupName
version      := artifactVersionNumber

libraryDependencies ++= {
  Seq(
    akkaActor,
    akkaStream,
    akkaPersistence,
    akkaSlf4j,
    reflections,
    jodaTime,
    akkaPersistenceCassandra,
    akkaPersistenceCassandraLauncher % Test,
    googleGuice % Test,
    slf4jApi % Test,
    slf4jSimple % Test,
    akkaTestkit % Test,
    scalaTest % Test
  )
}

publishTo := localRepo
