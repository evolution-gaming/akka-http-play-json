name := "akka-http-play-json"

organization := "com.evolutiongaming"

homepage := Some(new URL("http://github.com/evolution-gaming/akka-http-play-json"))

startYear := Some(2016)

publishMavenStyle := true

organizationName := "Evolution Gaming"

resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

organizationHomepage := Some(url("http://evolutiongaming.com"))

bintrayOrganization := Some("evolutiongaming")

scalaVersion := "2.11.11"

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

scalacOptions in (Compile,doc) ++= Seq("-groups", "-implicits", "-no-link-warnings")

libraryDependencies ++= {
  val AkkaHttpVersion = "10.0.9"
  val PlayJsonVersion = "2.5.14"

  Seq(
    "com.typesafe.akka" %% "akka-http-core" % AkkaHttpVersion,
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    "com.typesafe.play" %% "play-json" % PlayJsonVersion
  )
}

licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")))