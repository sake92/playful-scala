package ba.sake.server.controllers

import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import play.api.mvc.PlayBodyParsers
import play.api.libs.json.Json
import play.api.libs.json.JsError
import ba.sake.server.routing.PlayfulRouter
import ba.sake.shared.api.routes._
import ba.sake.shared.api.models.user.User

class UserController(val Action: DefaultActionBuilder, val parse: PlayBodyParsers) extends PlayfulRouter {

  private val users = List(User(1, "Sake", "Sarajevo"), User(2, "Meho", "Berlin"), User(3, "Hamo", "Chicago"))

  override val playfulRoutes = {
    case (GET, UserByIdRoute(userId)) =>
      Action {
        val user = users.find(_.id == userId).get
        Ok(Json.toJson(user))
      }
    case (GET, UsersRoute()) =>
      Action {
        Ok(Json.toJson(users))
      }
    case (POST, UsersRoute()) =>
      Action(parse.json) { req =>
        println(req.body)
        req.body.validate[User].fold(
          errors => {
            BadRequest(Json.obj("message" -> JsError.toJson(errors)))
          },
          user => {
            Ok(Json.toJson(user))
          }
        )

      }
  }

}
