import sbt._

object Dependencies {
  lazy val akkaVersion = "2.5.12"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val typesafeConfig = "com.typesafe" % "config" % "1.3.2"
  lazy val akka =  "com.typesafe.akka" %% "akka-actor" % akkaVersion
  lazy val akkaTest = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
}
