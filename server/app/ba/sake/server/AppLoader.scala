package ba.sake.server

import _root_.controllers.AssetsComponents
import play.api._
import play.api.ApplicationLoader.Context
import play.api.mvc._
import play.api.routing.Router
import ba.sake.server.controllers._
import ba.sake.server.services._

class AppLoader extends ApplicationLoader {

  def load(context: Context) = new AppComponents(context).application
}

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with AssetsComponents
    with NoHttpFiltersComponents {

  override val router = {

    val userService = new UserService

    val apiControllers = List(
      new UserController(Action, PlayBodyParsers(), userService)
    )
    val apiRoutes = apiControllers.map(_.routes)
      .foldLeft(PartialFunction.empty[RequestHeader, Handler])(_ orElse _)
    val apiRouter = Router.from(apiRoutes).withPrefix("/api")

    val mainController = new MainController(Action, assets, context.environment.mode)
    val mainRouter = Router.from(mainController.routes)

    apiRouter orElse mainRouter
  }
}
