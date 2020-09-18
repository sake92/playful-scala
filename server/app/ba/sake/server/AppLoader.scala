package ba.sake.server

import play.api.ApplicationLoader
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.NoHttpFiltersComponents
import play.api.routing.Router
import play.api.mvc.RequestHeader
import play.api.mvc.Handler
import _root_.controllers.AssetsComponents
import ba.sake.server.controllers._

class AppLoader extends ApplicationLoader {

  def load(context: Context) = new AppComponents(context).application
}

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with AssetsComponents
    with NoHttpFiltersComponents {

  override val router = {
    val controllers = List(
      new UserController(Action),
      new BlogPostController(Action),
      new MainController(Action, assets)
    )
    val routes = controllers
      .map(_.routes)
      .foldLeft(PartialFunction.empty[RequestHeader, Handler])(_ orElse _)
    Router.from(routes)
  }

}
