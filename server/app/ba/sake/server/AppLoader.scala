package ba.sake.server

import _root_.controllers.AssetsComponents
import play.api._
import play.api.mvc._
import play.api.ApplicationLoader.Context
import play.api.routing.Router
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
      new UserController(Action, PlayBodyParsers()),
      new BlogPostController(Action),
      new MainController(Action, assets, context.environment.mode)
    )
    val routes = controllers.map(_.routes)
      .foldLeft(PartialFunction.empty[RequestHeader, Handler])(_ orElse _)

    Router.from(routes)
  }

}
