package ba.sake.server.routing

import play.api.mvc.Handler
import play.api.routing.Router
import play.api.routing.SimpleRouter
import play.api.mvc.RequestHeader

object PlayfulRouter {
  type Routes = PartialFunction[(String, String), Handler]

  def from(routes: Routes): Router.Routes = rh => routes((rh.method, rh.uri))
}

trait PlayfulRouter extends SimpleRouter {

  def playfulRoutes: PlayfulRouter.Routes

  // avoid using SIRD macros
  override def routes: Router.Routes = {

    val f = (rh: RequestHeader) => {
      val methAndUri = (rh.method, rh.uri)
      if (playfulRoutes.isDefinedAt(methAndUri)) Some(playfulRoutes(methAndUri))
      else None
    }

    f.unlift
  }

}
