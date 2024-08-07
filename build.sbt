name := "akka-http-play-json"

organization := "com.evolutiongaming"

homepage := Some(url("https://github.com/evolution-gaming/akka-http-play-json"))

startYear := Some(2016)

publishMavenStyle := true

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

publishTo := Some(Resolver.evolutionReleases)

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.5", "2.12.13")

releaseCrossBuild := true

Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.6.8",
  "com.typesafe.akka" %% "akka-http"   % "10.2.4",
  "com.typesafe.play" %% "play-json"   % "2.9.2")

licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")))

//addCommandAlias("check", "all versionPolicyCheck Compile/doc")
addCommandAlias("check", "show version")
addCommandAlias("build", "+all compile test")
