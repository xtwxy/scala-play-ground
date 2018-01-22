import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.github.xtwxy.scala.playground.jodatime",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Joda Time Playground",
    libraryDependencies ++= {
        val jodaTimeVersion = "2.9.9"
        Seq(
            "joda-time"                 %   "joda-time"      % jodaTimeVersion,
            scalaTest % Test
        )
    }
  )
