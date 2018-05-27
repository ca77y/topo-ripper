import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "pl.tom",
      scalaVersion := "2.12.6",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "topo-ripper",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      typesafeConfig,
      akka,
      akkaTest % Test
    )
  )

unmanagedSourceDirectories in Test += baseDirectory.value / "src/test/e2e"
