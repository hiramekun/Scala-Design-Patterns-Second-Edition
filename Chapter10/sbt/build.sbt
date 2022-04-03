organization := "com.ivan.nikolov"

name := "deep-theory-sbt"

scalaVersion := "2.12.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

javaOptions ++= Seq("-target", "1.8", "-source", "1.8")

publishMavenStyle := true

libraryDependencies ++= {
  Seq(
    "org.typelevel" %% "cats-core" % "2.3.0",
    "org.slf4j" % "slf4j-log4j12" % "1.7.25",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test",
    "org.mockito" % "mockito-all" % "1.10.19" % "test" // mockito for tests
  )
}