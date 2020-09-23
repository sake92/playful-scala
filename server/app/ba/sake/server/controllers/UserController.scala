package ba.sake.server.controllers

import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import play.api.libs.json.Json
import ba.sake.server.routing.PlayfulRouter
import ba.sake.shared.api.routes._
import ba.sake.shared.api.models.user.User

class UserController(val Action: DefaultActionBuilder) extends PlayfulRouter {

  private var users = List(
    User(1, "Sake", "Sarajevo"),
    User(2, "Amer", "Hoffenheim")
  )

  override val playfulRoutes = {
    case ("GET", UserByIdRoute(userId)) =>
      Action {
        val user = users.find(_.id == userId).get

        Ok(Json.toJson(user))
      }
    case ("GET", UsersRoute()) =>
      Action {
        Ok(Json.toJson(users))
      }
  }

}
