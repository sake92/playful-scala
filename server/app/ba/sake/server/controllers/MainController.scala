package ba.sake.server.controllers

import controllers.Assets
import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import play.api.Mode
import ba.sake.hepek.play.implicits._
import ba.sake.shared.api.routes.AssetRoute
import ba.sake.server.views.IndexPage

class MainController(val Action: DefaultActionBuilder, val assets: Assets, mode: Mode)
    extends PlayfulController {

  override val playfulRoutes = {

    case GET -> AssetRoute(file) =>
      assets.at("/public", file)

    case GET -> _ =>
      Action {
        Ok(IndexPage(mode))
      }
  }

}
