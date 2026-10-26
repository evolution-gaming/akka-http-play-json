lazy val ScalaVersions = Seq("2.13.18")

ThisBuild / scalaVersion := ScalaVersions.head

lazy val commonSettings = Seq(
  organization := "com.evolutiongaming",
  homepage := Some(uri("https://github.com/evolution-gaming/akka-http-play-json")),
  startYear := Some(2016),
  publishMavenStyle := true,
  organizationName := "Evolution",
  organizationHomepage := Some(uri("https://evolution.com")),
  publishTo := Some(Resolver.evolutionReleases),
  licenses := Seq(("Apache-2.0", uri("http://www.apache.org/licenses/LICENSE-2.0"))),
  crossScalaVersions := ScalaVersions,
  Compile / doc / scalacOptions ++= Seq(
    "-groups",
    "-implicits",
    "-no-link-warnings",
  ),
  libraryDependencies ++= Seq(
    "org.playframework" %% "play-json" % "3.0.6",
    "com.fasterxml.jackson.core" % "jackson-core" % "2.22.4",
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.22.4",
  ),
  versionPolicyIntention := Compatibility.BinaryCompatible,
)

lazy val root = (project in file("."))
  .aggregate(`root-pekko`)
  .settings(commonSettings)
  .settings(
    moduleName := "akka-http-play-json",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.6.21", // `2.6.21` is last open source version before switch to BSL
      "com.typesafe.akka" %% "akka-http" % "10.2.10", // `10.2.10` is last open source version before switch to BSL
    ),
  )

lazy val `root-pekko` = (project in file("pekko"))
  .settings(commonSettings)
  .settings(
    moduleName := "pekko-http-play-json",
    crossScalaVersions := Seq("2.13.18", "3.3.8"),
    Compile / unmanagedSourceDirectories +=
      (LocalRootProject / Compile / scalaSource).value,
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-stream" % "1.7.1",
      "org.apache.pekko" %% "pekko-http" % "1.4.2",
      "com.evolution" %% "akka-to-pekko-adapter-stream" % "1.0.6",
      "com.evolution" %% "akka-to-pekko-adapter-http" % "1.0.6",
      "com.evolution" %% "akka-to-pekko-adapter-actor" % "1.0.6",
    ),
  )

addCommandAlias("check", "all scalafmtCheckRepo versionPolicyCheck Compile/doc")
addCommandAlias("fmt", "scalafmtRepo")
addCommandAlias("build", "+testFull; +publish")
