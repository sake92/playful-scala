package ba.sake.server.controllers

import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import controllers.Assets
import ba.sake.server.routing.PlayfulRouter
import ba.sake.shared.ServerRoutes.AssetRoute
import ba.sake.server.views.IndexPage
import ba.sake.hepek.play.implicits._

class IndexController(val Action: DefaultActionBuilder, val assets: Assets) extends PlayfulRouter {

  override val playfulRoutes = {
    case ("GET", AssetRoute(file)) =>
      assets.at("/public", file)

    case ("GET", _) =>
      Action {
        Ok(IndexPage)
      }
  }

}
