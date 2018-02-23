# Notes

play-socket.io cannot be compiled with default `plugin.sbt` configuration,
because sbt-mima-plugin@0.1.13 is not available.

the following changes are made
```
-addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.1.13")
+addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.1.18")
```
and the problem fixed.
