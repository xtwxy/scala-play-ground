import Dependencies._

name := "cluster"
scalaVersion := scalaVersionNumber
organization := ecommerceGroupName
version      := artifactVersionNumber

enablePlugins(JavaAppPackaging)
enablePlugins(JavaServerAppPackaging)
scriptClasspath += confPath

parallelExecution in Test := false

fork := true

libraryDependencies ++= {
  Seq(
    akkaActor,
    akkaStream,
    akkaPersistence,
    akkaCluster,
    akkaClusterTools,
    akkaClusterSharding,
    akkaSlf4j,
    playGuice,
    akkaPersistenceCassandra % Test,
    akkaPersistenceCassandraLauncher % Test,
    reflections % Test,
    jodaTime % Test,
    slf4jApi % Test,
    slf4jSimple % Test,
    scalaTest % Test,
    akkaTestkit % Test,
    akkaMultiNodeTestKit % Test,
    scalaTestPlusPlay % Test
  )
}


publishTo := localRepo

