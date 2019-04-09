import Dependencies._

name := "service"
scalaVersion := scalaVersionNumber
organization := ecommerceGroupName
version      := artifactVersionNumber

libraryDependencies ++= {
  Seq(
    akkaActor,
    akkaSlf4j,
    playGuice,
    slf4jApi % Test,
    slf4jSimple % Test,
    playTest % Test,
    scalaTest % Test,
    akkaTestkit % Test,
    akkaMultiNodeTestKit % Test,
    scalaTestPlusPlay % Test
  )
}

publishTo := localRepo

