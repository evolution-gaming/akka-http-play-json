package akka.http.scaladsl.marshallers.playjson

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.server.{RejectionError, ValidationRejection}
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import akka.util.ByteString
import play.api.libs.json._

/**
  * Automatic to and from JSON marshalling/unmarshalling using an in-scope *play-json* protocol.
  */
trait PlayJsonSupport {
  import PlayJsonSupport._

  private val jsonStringUnmarshaller =
    Unmarshaller.byteStringUnmarshaller
      .forContentTypes(`application/json`)
      .mapWithCharset {
        case (ByteString.empty, _) => throw Unmarshaller.NoContentException
        case (data, charset)       => data.decodeString(charset.nioCharset.name)
      }

  private val jsonStringMarshaller: ToEntityMarshaller[String] = {

    /* This is hack to add UTF-8 charset to application/json content type.

     * Despite RFC4627 application/json shouldn't contain
     * charset: http://www.iana.org/assignments/media-types/application/json
     *
     * But sometimes we have bugs most likely because of https://bugzilla.mozilla.org/show_bug.cgi?id=741776
     */

    Marshaller.withFixedContentType(`application/json`) {HttpEntity(`application/json; charset=UTF-8`, _)}
  }

  /**
    * HTTP entity => `A`
    *
    * @param reads reader for `A`
    * @tparam A type to decode
    * @return unmarshaller for `A`
    */
  implicit def playJsonUnmarshaller[A](implicit reads: Reads[A]): FromEntityUnmarshaller[A] = {
    def read(json: JsValue) = reads.reads(json) recoverTotal { error =>
      throw RejectionError(ValidationRejection(JsError.toJson(error).toString, Some(PlayJsonError(error))))
    }

    jsonStringUnmarshaller.map(data => read(Json.parse(data)))
  }

  /**
    * `A` => HTTP entity
    *
    * @param writes writer for `A`
    * @param printer pretty printer function
    * @tparam A type to encode
    * @return marshaller for any `A` value
    */
  implicit def playJsonMarshaller[A](implicit
    writes: Writes[A],
    printer: JsValue => String = Json.prettyPrint): ToEntityMarshaller[A] = {

    jsonStringMarshaller.compose(printer).compose(writes.writes)
  }
}

object PlayJsonSupport extends PlayJsonSupport {

  val `application/json; charset=UTF-8` = MediaType.customWithOpenCharset("application", "json") withCharset HttpCharsets.`UTF-8`

  case class PlayJsonError(error: JsError) extends RuntimeException {
    override def getMessage: String = JsError.toJson(error).toString()
  }

}
