import sbt._
import scalapb.compiler.Version.scalapbVersion

object Dependencies {
  lazy val scalaVersionNumber    = "2.12.4"
  lazy val akkaVersion           = "2.5.11"
  lazy val groupName             = "com.github.xtwxy.scala.playground"
  lazy val artifactVersionNumber = "1.0.0"
  lazy val sprayVersion          = "1.3.3"
  lazy val playVersion           = "2.6.9"
  lazy val playSilhouetteVersion = "5.0.3"

  lazy val scalaXml        = "org.scala-lang.modules"    %%  "scala-xml"                           % "1.0.6"
  lazy val akkaActor       = "com.typesafe.akka"         %%  "akka-actor"                          % akkaVersion
  lazy val akkaRemote      = "com.typesafe.akka"         %%  "akka-remote"                         % akkaVersion
  lazy val akkaParsing     = "com.typesafe.akka"         %%  "akka-parsing"                        % "10.1.1"
  lazy val akkaStream      = "com.typesafe.akka"         %%  "akka-stream"                         % akkaVersion
  lazy val akkaStreamCassandra = "com.lightbend.akka"    %%  "akka-stream-alpakka-cassandra"       % "0.18"
  lazy val akkaPersistence = "com.typesafe.akka"         %%  "akka-persistence"                    % akkaVersion
  lazy val akkaPersistenceQuery = "com.typesafe.akka"    %% "akka-persistence-query"               % akkaVersion
  lazy val akkaPersistenceCassandra = "com.typesafe.akka"         %%  "akka-persistence-cassandra"          % "0.83"
  lazy val akkaPersistenceCassandraLauncher = "com.typesafe.akka"         %%  "akka-persistence-cassandra-launcher" % "0.83"
  lazy val akkaCluster     = "com.typesafe.akka"         %%  "akka-cluster"                        % akkaVersion
  lazy val akkaClusterTools= "com.typesafe.akka"         %%  "akka-cluster-tools"                  % akkaVersion
  lazy val akkaClusterMetrics = "com.typesafe.akka"         %%  "akka-cluster-metrics"                        % akkaVersion
  lazy val akkaClusterSharding = "com.typesafe.akka"         %%  "akka-cluster-sharding"               % akkaVersion
  lazy val akkaSlf4j       = "com.typesafe.akka"         %%  "akka-slf4j"                          % akkaVersion
  lazy val akkaTestkit     = "com.typesafe.akka"         %%  "akka-testkit"                        % akkaVersion
  lazy val playSilhouette  = "com.mohiva"                %%  "play-silhouette"                     % playSilhouetteVersion
  lazy val play            = "com.typesafe.play"         %%  "play"                                % playVersion
  lazy val webjarsPlay     = "org.webjars"               %%  "webjars-play"                        % "2.6.1"
  lazy val webjarsJquery   = "org.webjars"               %   "jquery"                              % "3.2.1"
  lazy val webjarsJqueryI18n= "org.webjars.bower"        % "jquery-i18n-properties"                % "1.2.7"
  lazy val webjarsRequireJS = "org.webjars"              % "requirejs"                             % "2.3.5"
  lazy val webjarsEasyUI   = "org.webjars.bower"         % "github-com-novaeye-jquery-easyui-bower" % "1.5.0.1"
  lazy val webjarsYUI      = "org.webjars"               % "yui"                                   % "3.18.1"
  lazy val playTest        = "com.typesafe.play"         %%  "play-test"                           % playVersion
  lazy val playSilhouetteTest =  "com.mohiva"                %%  "play-silhouette-testkit"             % playSilhouetteVersion
  lazy val akkaMultiNodeTestKit = "com.typesafe.akka"         %%  "akka-multi-node-testkit"             % akkaVersion
  lazy val leveldb         = "org.iq80.leveldb"          % "leveldb"                               % "0.7"
  lazy val leveldbjniAll   = "org.fusesource.leveldbjni" % "leveldbjni-all"                        % "1.8"
  lazy val reflections     = "org.reflections"           %   "reflections"                         % "0.9.11"
  lazy val jodaTime        = "joda-time"                 %   "joda-time"                           % "2.9.9"
  lazy val playAnorm       = "com.typesafe.play"         %%  "anorm"                               % "2.6.0-M1"
  lazy val googleGuice     = "com.google.inject"         %   "guice"                               % "4.2.0"
  lazy val playGuice       = "com.typesafe.play"         %%  "play-guice"                          % playVersion
  lazy val playSlick       = "com.typesafe.play"         %%  "play-slick"                          % "3.0.2"
  lazy val playJson        = "com.typesafe.play"         %%  "play-json"                           % playVersion
  lazy val mysqlDriver     = "mysql"                     %   "mysql-connector-java"                % "6.0.6"
  lazy val jgraphtCore     = "org.jgrapht"               %   "jgrapht-core"                        % "1.1.0"
  lazy val h2              = "com.h2database"            %   "h2"                                  % "1.4.196"
  lazy val scalapbCompiler = "com.thesamet.scalapb"      %% "compilerplugin"                       % scalapbVersion
  lazy val scalapbRuntime  = "com.thesamet.scalapb"      %% "scalapb-runtime"                      % scalapbVersion
  lazy val protoSerializer = "com.github.apuex.protobuf" %   "protobuf-serializer"                 % "1.0.0"

  lazy val slf4jApi        = "org.slf4j"                 %  "slf4j-api"                            % "1.7.25"
  lazy val slf4jSimple     = "org.slf4j"                 %  "slf4j-simple"                         % "1.7.25"
  lazy val logbackClassic  = "ch.qos.logback"            %   "logback-classic"                     % "1.2.3"
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

  lazy val confPath = "../conf"
  lazy val localRepo = Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
}
