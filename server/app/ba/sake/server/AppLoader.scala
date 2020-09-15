package ba.sake.server

import play.api.ApplicationLoader
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.routing.Router
import play.api.mvc.Results._
import ba.sake.shared.ServerRoutes.UserByIdRoute

class AppLoader extends ApplicationLoader {

  def load(context: Context) = new AppComponents(context).application
}

class AppComponents(context: Context) extends BuiltInComponentsFromContext(context) {

  override val httpFilters = Nil

  val router: Router = Router.from { rh =>
    (rh.method, rh.uri) match {
      case ("GET", UserByIdRoute(userId)) =>
        Action {
          Ok(s"GET User by id=$userId")
        }
    }
  }
}
