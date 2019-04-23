addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.9")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.3.6")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.20")

addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.20")
libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.8.1"

