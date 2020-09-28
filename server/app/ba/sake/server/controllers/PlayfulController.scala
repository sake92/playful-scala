package ba.sake.server.controllers

import scala.concurrent.ExecutionContext
import play.api.mvc.PlayBodyParsers
import ba.sake.server.routing.PlayfulRouter

trait PlayfulController extends PlayfulRouter

trait PlayfulJsonController extends PlayfulController with JsonHelpers

trait JsonHelpers {
  import play.api.mvc.Results.BadRequest
  import play.api.libs.json._

  implicit val ec: ExecutionContext

  def parse: PlayBodyParsers

  def validateJson[A: Reads] =
    parse.json.validate(
      _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
    )
}
