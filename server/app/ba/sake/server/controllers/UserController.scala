package ba.sake.server.controllers

import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import ba.sake.server.routing.PlayfulRouter
import ba.sake.shared.ServerRoutes.UserByIdRoute
import play.api.libs.json.Json
import ba.sake.shared.api.models.user.User

class UserController(val Action: DefaultActionBuilder) extends PlayfulRouter {

  override val playfulRoutes = {
    case ("GET", UserByIdRoute(userId)) =>
      Action {
        val json = Json.toJson(User(userId, s"user$userId"))
        Ok(json)
      }
  }

}
