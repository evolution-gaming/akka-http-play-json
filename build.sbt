lazy val ScalaVersions = Seq("2.13.18")

ThisBuild / scalaVersion := ScalaVersions.head

lazy val commonSettings = Seq(
  organization := "com.evolutiongaming",
  homepage := Some(
    uri("https://github.com/evolution-gaming/akka-http-play-json")
  ),
  startYear := Some(2016),
  publishMavenStyle := true,
  organizationName := "Evolution",
  organizationHomepage := Some(uri("https://evolution.com")),
  publishTo := Some(Resolver.evolutionReleases),
  licenses := Seq(
    ("Apache-2.0", uri("http://www.apache.org/licenses/LICENSE-2.0"))
  ),
  crossScalaVersions := ScalaVersions,
  Compile / doc / scalacOptions ++= Seq(
    "-groups",
    "-implicits",
    "-no-link-warnings"
  ),
  libraryDependencies ++= Seq(
    "org.playframework" %% "play-json" % "3.0.6"
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    moduleName := "akka-http-play-json",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.6.21", // `2.6.21` is last open source version before switch to BSL
      "com.typesafe.akka" %% "akka-http" % "10.2.10" // `10.2.10` is last open source version before switch to BSL
    )
  )

lazy val `root-pekko` = (project in file("pekko"))
  .settings(commonSettings)
  .settings(
    moduleName := "pekko-http-play-json",
    Compile / unmanagedSourceDirectories +=
      (root / Compile / scalaSource).value,
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-stream" % "1.6.0",
      "org.apache.pekko" %% "pekko-http" % "1.4.0",
      "com.evolution" %% "akka-to-pekko-adapter-stream" % "1.0.4",
      "com.evolution" %% "akka-to-pekko-adapter-http" % "1.0.4",
      "com.evolution" %% "akka-to-pekko-adapter-actor" % "1.0.4"
    )
  )

//addCommandAlias("check", "all versionPolicyCheck Compile/doc")
addCommandAlias("check", "all root/compile root-pekko/compile")
addCommandAlias(
  "build",
  "all root/compile root-pekko/compile root/publish root-pekko/publish"
)
