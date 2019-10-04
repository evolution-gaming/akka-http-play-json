name := "akka-http-play-json"

organization := "com.evolutiongaming"

homepage := Some(new URL("http://github.com/evolution-gaming/akka-http-play-json"))

startYear := Some(2016)

publishMavenStyle := true

organizationName := "Evolution Gaming"

resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

organizationHomepage := Some(url("http://evolutiongaming.com"))

bintrayOrganization := Some("evolutiongaming")

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.0", "2.12.9")

releaseCrossBuild := true

Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

libraryDependencies ++= {
  val AkkaVersion = "2.5.25"
  val AkkaHttpVersion = "10.1.10"
  val PlayJsonVersion = "2.7.4"

  Seq(
    "com.typesafe.akka" %% "akka-stream"    % AkkaVersion,
    "com.typesafe.akka" %% "akka-actor"     % AkkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % AkkaHttpVersion,
    "com.typesafe.akka" %% "akka-http"      % AkkaHttpVersion,
    "com.typesafe.play" %% "play-json"      % PlayJsonVersion)
}

licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")))