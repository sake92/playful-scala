package ba.sake.server

import play.api.ApplicationLoader
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.NoHttpFiltersComponents
import play.api.routing.Router
import ba.sake.server.controllers._

class AppLoader extends ApplicationLoader {

  def load(context: Context) = new AppComponents(context).application
}

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with NoHttpFiltersComponents {

  override val router = {
    val userController = new UserController(Action)
    val blogPostController = new BlogPostController(Action)
    val routes = userController.routes orElse blogPostController.routes
    Router.from(routes)
  }

}
