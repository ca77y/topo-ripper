import sbt._

object Dependencies {
  lazy val akkaVersion = "2.5.12"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val typesafeConfig = "com.typesafe" % "config" % "1.3.2"
  lazy val akka =  "com.typesafe.akka" %% "akka-actor" % akkaVersion
  lazy val akkaTest = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
  lazy val sttp = "com.softwaremill.sttp" %% "core" % "1.2.1"
  lazy val sttpJson4s = "com.softwaremill.sttp" %% "json4s" % "1.2.1"
  lazy val jsoup = "org.jsoup" % "jsoup" % "1.11.3"
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
  lazy val json4s =  "org.json4s" %% "json4s-jackson" % "3.5.4"
  lazy val kml = "com.github.workingDog" %% "scalakml" % "1.4"
}
