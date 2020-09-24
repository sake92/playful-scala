package ba.sake.server.controllers

import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import play.api.mvc.PlayBodyParsers
import play.api.libs.json.Json
import play.api.libs.json.JsError
import ba.sake.server.routing.PlayfulRouter
import ba.sake.shared.api.routes._
import ba.sake.shared.api.models.user._

class UserController(val Action: DefaultActionBuilder, val parse: PlayBodyParsers) extends PlayfulRouter {

  private var userIdCounter = 0
  private var users = List(
    User(getUserId(), "Sake", "Sarajevo"),
    User(getUserId(), "Meho", "Berlin"),
    User(getUserId(), "Hamo", "Chicago")
  )

  override val playfulRoutes = {

    case (GET, UserByIdRoute(userId)) => Action {
        val user = users.find(_.id == userId).get
        Ok(Json.toJson(user))
      }

    case (GET, UsersRoute()) => Action {
        Ok(Json.toJson(users))
      }

    case (POST, UsersRoute()) => Action(parse.json) { req =>
        req.body.validate[CreateUserRequest].fold(
          errors => {
            BadRequest(Json.obj("message" -> JsError.toJson(errors)))
          },
          createUserReq => {
            val newUser = User(getUserId(), createUserReq.username, createUserReq.address)
            users = users.appended(newUser)
            Ok(Json.toJson(newUser))
          }
        )

      }
  }

  private def getUserId(): Long = {
    userIdCounter += 1
    userIdCounter
  }

}
