organization := "com.ivan.nikolov"

name := "functional-design-patterns-sbt"

scalaVersion := "2.13.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

javaOptions ++= Seq("-target", "1.8", "-source", "1.8")

publishMavenStyle := true

libraryDependencies ++= {
  Seq(
    "org.slf4j" % "slf4j-log4j12" % "1.7.25",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
    "commons-codec" % "commons-codec" % "1.11",
    "org.scalaz" %% "scalaz-core" % "7.3.6",
    "com.h2database" % "h2" % "1.4.196",
    "org.scalactic" %% "scalactic" % "3.2.11",
    "dev.optics" %% "monocle-core"  % "3.1.0",
    "dev.optics" %% "monocle-macro" % "3.1.0",
    "org.scalatest" %% "scalatest" % "3.2.11" % "test",
    "org.mockito" %% "mockito-scala" % "1.16.0" % "test"
  )
}