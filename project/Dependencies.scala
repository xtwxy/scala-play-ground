import sbt._

object Dependencies {
  lazy val scalaVersionNumber    = "2.12.3"
  lazy val akkaVersion           = "2.5.6"
  lazy val groupName             = "com.github.xtwxy.scala.playground"
  lazy val artifactVersionNumber = "1.0.0"

  lazy val scalaXml        = "org.scala-lang.modules"    %%  "scala-xml"                           % "1.0.6"
  lazy val akkaActor       = "com.typesafe.akka"         %%  "akka-actor"                          % akkaVersion
  lazy val akkaStream      = "com.typesafe.akka"         %%  "akka-stream"                         % akkaVersion
  lazy val akkaStreamCassandra = "com.typesafe.akka"     %%  "akka-stream-alpakka-cassandra"       % "21d5ff44+20161107-1338"
  lazy val akkaPersistence = "com.typesafe.akka"         %%  "akka-persistence"                    % akkaVersion
  lazy val akkaCluster     = "com.typesafe.akka"         %%  "akka-cluster"                        % akkaVersion
  lazy val akkaClusterMetrics = "com.typesafe.akka"         %%  "akka-cluster-metrics"                        % akkaVersion
  lazy val akkaSlf4j       = "com.typesafe.akka"         %%  "akka-slf4j"                          % akkaVersion
  lazy val akkaTestkit     = "com.typesafe.akka"         %%  "akka-testkit"                        % akkaVersion
  lazy val akkaMultiNodeTestKit = "com.typesafe.akka"         %%  "akka-multi-node-testkit"             % akkaVersion
  lazy val reflections     = "org.reflections"           %   "reflections"                         % "0.9.11"
  lazy val jodaTime        = "joda-time"                 %   "joda-time"                           % "2.9.9"
  lazy val h2              = "com.h2database"            %   "h2"                                  % "1.4.196"

  lazy val logback         = "ch.qos.logback"            %   "logback-classic"                     % "1.2.3"
  lazy val scalaTest       = "org.scalatest"             %% "scalatest"                            % "3.0.4"
  lazy val scalaTesplusPlay= "org.scalatestplus.play"    %%  "scalatestplus-play"                  % "3.1.2"
  lazy val guava           = "com.google.guava"          %   "guava"                               % "22.0"
  lazy val playSocketIO    = "com.lightbend.play"        %%  "play-socket-io"                      % "1.0.0-beta-2"
  lazy val macwireMicros   = "com.softwaremill.macwire"  %%  "macros"                              % "2.3.0"
  lazy val scalacheck      = "org.scalacheck"            %%  "scalacheck"                          % "1.13.4"
  lazy val scalaTestPlusPlay = "org.scalatestplus.play"  %%  "scalatestplus-play"                  % "3.1.2"

  lazy val dependedRepos = Seq(
      "Atlassian Releases" at "https://maven.atlassian.com/public/",
      "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
      Resolver.sonatypeRepo("snapshots")
  )

  lazy val localRepo = Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
}
