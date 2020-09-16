package ba.sake.server

import play.api.ApplicationLoader
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.NoHttpFiltersComponents
import play.api.routing.Router
import ba.sake.server.controllers._
import _root_.controllers.AssetsComponents

class AppLoader extends ApplicationLoader {

  def load(context: Context) = new AppComponents(context).application
}

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with AssetsComponents
    with NoHttpFiltersComponents {

      // TODO handle Assets...
  override val router = {
    val userController = new UserController(Action)
    val blogPostController = new BlogPostController(Action)
    val indexController = new IndexController(Action, assets)
    val routes = userController.routes orElse blogPostController.routes orElse indexController.routes
    Router.from(routes)
  }

}
