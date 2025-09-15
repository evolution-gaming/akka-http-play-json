package org.apache.pekko.http.scaladsl.marshallers

package object playjson {
  type PlayJsonSupport = akka.http.scaladsl.marshallers.playjson.PlayJsonSupport
  val PlayJsonSupport
      : akka.http.scaladsl.marshallers.playjson.PlayJsonSupport.type =
    akka.http.scaladsl.marshallers.playjson.PlayJsonSupport
}
