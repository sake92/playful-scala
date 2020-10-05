package ba.sake.server.controllers

import scala.concurrent.ExecutionContext
import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import play.api.mvc.PlayBodyParsers
import play.api.libs.json.Json
import ba.sake.shared.api.routes._
import ba.sake.shared.api.models.user._
import ba.sake.server.services.UserService

class UserController(
    val Action: DefaultActionBuilder,
    val parse: PlayBodyParsers,
    val userService: UserService
)(implicit val ec: ExecutionContext)
    extends PlayfulJsonController {

  override val playfulRoutes = {

    case GET -> UserByIdRoute(userId) => Action {
        userService.findById(userId) match {
          case Some(user) => Ok(Json.toJson(user))
          case None       => NotFound
        }
      }

    case GET -> UsersRoute() => Action {
        Ok(Json.toJson(userService.findAll()))
      }

    case POST -> UsersRoute() => Action(validateJson[CreateOrUpdateUserReq]) { req =>
        val newUser = userService.create(req.body)
        Ok(Json.toJson(newUser))
      }

    case PUT -> UserByIdRoute(userId) => Action(validateJson[CreateOrUpdateUserReq]) { req =>
        val updatedUser = userService.update(userId, req.body)
        Ok(Json.toJson(updatedUser))
      }

    case DELETE -> UserByIdRoute(userId) => Action { req =>
        userService.delete(userId)
        Ok
      }
  }

}
