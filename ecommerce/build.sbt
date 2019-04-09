import Dependencies._

name         := "ecommerce"
scalaVersion := scalaVersionNumber
organization := ecommerceGroupName
version      := artifactVersionNumber

// pipelineStages := Seq(digest,gzip)

lazy val root = (project in file("."))
  .aggregate(
    dbschema,
    message,
    domain,
    cluster,
    service,
    rest
  )

lazy val dbschema = (project in file("dbschema"))
lazy val message = (project in file("message"))
lazy val domain = (project in file("domain")).dependsOn(message)
lazy val cluster = (project in file("cluster")).dependsOn(domain)
lazy val service = (project in file("service")).dependsOn(cluster)
lazy val rest = (project in file("rest"))
  .enablePlugins(PlayScala, RoutesCompiler)
  .dependsOn(service)

resolvers += "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"
publishTo := localRepo
