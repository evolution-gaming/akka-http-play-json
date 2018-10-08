## PlayJson for AkkaHttp

[![Build Status](https://travis-ci.org/evolution-gaming/akka-http-play-json.svg?branch=master)](https://travis-ci.org/evolution-gaming/akka-http-play-json)
[![Download](https://api.bintray.com/packages/evolutiongaming/maven/akka-http-play-json/images/download.svg) ](https://bintray.com/evolutiongaming/maven/akka-http-play-json/_latestVersion)

This project contains implicit marshaler and unmarshaler to enable simple endpoint and consumer development.  

## Installation

```scala
libraryDependencies ++= Seq(
  "com.evolutiongaming" %% "akka-http-play-json" % "0.1.11",
  ...
)
```

## Use
```scala
import akka.http.scaladsl.marshallers.playjson.PlayJsonSupport._
```