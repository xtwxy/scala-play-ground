import Dependencies._

name         := "socket.io-hello"
scalaVersion := scalaVersionNumber
organization := groupName
version      := artifactVersionNumber

lazy val socketio_hello = (project in file("."))
  .enablePlugins(GraalVMNativeImagePlugin)
  .enablePlugins(PlayScala)

libraryDependencies ++= {
  Seq(
    akkaActor,
    guice,
    playSocketIO,
    jodaTime,
    "com.softwaremill.macwire"  %%  "macros"                               % "2.3.0"       % Provided,
    scalaTestPlusPlay   % Test,
    scalaTest           % Test
  )
}


