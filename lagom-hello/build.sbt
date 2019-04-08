organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `lagom-hello` = (project in file("."))
  .aggregate(`lagom-hello-api`, `lagom-hello-impl`, `lagom-hello-stream-api`, `lagom-hello-stream-impl`)

lazy val `lagom-hello-api` = (project in file("lagom-hello-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-hello-impl` = (project in file("lagom-hello-impl"))
  .enablePlugins(LagomScala)
  .enablePlugins(UniversalPlugin)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`lagom-hello-api`)

lazy val `lagom-hello-stream-api` = (project in file("lagom-hello-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-hello-stream-impl` = (project in file("lagom-hello-stream-impl"))
  .enablePlugins(LagomScala)
  .enablePlugins(UniversalPlugin)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`lagom-hello-stream-api`, `lagom-hello-api`)
