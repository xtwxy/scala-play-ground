import Dependencies._

name := "message"
scalaVersion := scalaVersionNumber
organization := ecommerceGroupName
version      := artifactVersionNumber

resolvers += "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"
libraryDependencies ++= Seq(
  googleGuice,
  jodaTime,
  akkaRemote,
  akkaParsing,
  akkaSlf4j,
  reflections,
  queryRuntime,
  scalapbCompiler,
  scalapbRuntime % "protobuf",
  slf4jApi % Test,
  slf4jSimple % Test,
  scalaTestPlusPlay % Test
)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

publishTo := localRepo
