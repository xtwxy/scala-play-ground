import Dependencies._

name := "rest"
scalaVersion := scalaVersionNumber
organization := ecommerceGroupName
version      := artifactVersionNumber

libraryDependencies ++= {
  Seq(
    playJson,
    playAnorm,
    playSlick,
    queryRuntime,
    playTest % Test,
    scalaTestPlusPlay % Test
  )
}


routesGenerator := InjectedRoutesGenerator

publishTo := localRepo
