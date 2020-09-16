package ba.sake.server.controllers

import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import ba.sake.server.routing.PlayfulRouter
import ba.sake.shared.ServerRoutes.UserByIdRoute

class UserController(val Action: DefaultActionBuilder) extends PlayfulRouter {

  override val playfulRoutes = {
    case ("GET", UserByIdRoute(userId)) =>
      Action {
        Ok(s"GET User by id=$userId")
      }
  }

}
