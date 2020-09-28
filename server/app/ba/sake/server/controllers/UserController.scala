package ba.sake.server.controllers

import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import play.api.mvc.PlayBodyParsers
import play.api.libs.json.Json
import play.api.libs.json.JsError
import ba.sake.server.routing.PlayfulRouter
import ba.sake.shared.api.routes._
import ba.sake.shared.api.models.user._
import ba.sake.server.services.UserService

class UserController(
    val Action: DefaultActionBuilder,
    val parse: PlayBodyParsers,
    val userService: UserService
) extends PlayfulRouter {

  override val playfulRoutes = {

    case (GET, UserByIdRoute(userId)) => Action {
        userService.findById(userId) match {
          case Some(user) => Ok(Json.toJson(user))
          case None       => NotFound
        }
      }

    case (GET, UsersRoute()) => Action {
        Ok(Json.toJson(userService.findAll()))
      }

    case (POST, UsersRoute()) => Action(parse.json) { req =>
        req.body.validate[CreateOrUpdateUserRequest].fold(
          errors => {
            BadRequest(Json.obj("message" -> JsError.toJson(errors)))
          },
          createUserReq => {
            val newUser = userService.create(createUserReq)
            Ok(Json.toJson(newUser))
          }
        )
      }

    case (PUT, UserByIdRoute(userId)) => Action(parse.json) { req =>
        req.body.validate[CreateOrUpdateUserRequest].fold(
          errors => {
            BadRequest(Json.obj("message" -> JsError.toJson(errors)))
          },
          updateUserReq => {
            val updatedUser = userService.update(userId, updateUserReq)
            Ok(Json.toJson(updatedUser))
          }
        )
      }
  }

}
