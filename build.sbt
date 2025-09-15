import sbt.internal.ProjectMatrix

lazy val ScalaVersions = Seq("2.13.16")

lazy val root = (projectMatrix in file("."))
  .settings(
    organization := "com.evolutiongaming",
    homepage := Some(
      url("https://github.com/evolution-gaming/akka-http-play-json")
    ),
    startYear := Some(2016),
    publishMavenStyle := true,
    organizationName := "Evolution",
    organizationHomepage := Some(url("https://evolution.com")),
    publishTo := Some(Resolver.evolutionReleases),
    Compile / doc / scalacOptions ++= Seq(
      "-groups",
      "-implicits",
      "-no-link-warnings"
    ),
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.10.7"
    )
  )
  .jvmPlatform(
    scalaVersions = ScalaVersions,
    axisValues = Seq(ConfigAxis.Provider.pekko),
    configure = _.settings(
      moduleName := "pekko-http-play-json",
      libraryDependencies ++= Seq(
        "org.apache.pekko" %% "pekko-stream" % "1.2.0",
        "org.apache.pekko" %% "pekko-http" % "1.2.0",
        "com.evolution" %% "akka-to-pekko-adapter-stream" % "1.0.2",
        "com.evolution" %% "akka-to-pekko-adapter-http" % "1.0.2",
        "com.evolution" %% "akka-to-pekko-adapter-actor" % "1.0.2"
      )
    )
  )
  .jvmPlatform(
    scalaVersions = ScalaVersions,
    axisValues = Seq(ConfigAxis.Provider.akka),
    configure = _.settings(
      moduleName := "akka-http-play-json",
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-stream" % "2.6.21",
        "com.typesafe.akka" %% "akka-http" % "10.2.10"
      )
    )
  )

licenses := Seq(
  ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
)

//addCommandAlias("check", "all versionPolicyCheck Compile/doc")
addCommandAlias("check", "all root/compile root-pekko/compile")
addCommandAlias(
  "build",
  "all root/compile root-pekko/compile root/publish root-pekko/publish"
)
