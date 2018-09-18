name := "akka-http-play-json"

organization := "com.evolutiongaming"

homepage := Some(new URL("http://github.com/evolution-gaming/akka-http-play-json"))

startYear := Some(2016)

publishMavenStyle := true

organizationName := "Evolution Gaming"

resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

organizationHomepage := Some(url("http://evolutiongaming.com"))

bintrayOrganization := Some("evolutiongaming")

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.6")

releaseCrossBuild := true

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture"
)

Compile/doc/scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

libraryDependencies ++= {
  val AkkaVersion = "2.5.16"
  val AkkaHttpVersion = "10.1.5"
  val PlayJsonVersion = "2.6.10"

  Seq(
    "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
    "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % AkkaHttpVersion,
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    "com.typesafe.play" %% "play-json" % PlayJsonVersion
  )
}

licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")))