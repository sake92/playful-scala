package ba.sake.server.routing

import play.api.mvc.Handler
import play.api.mvc.RequestHeader
import play.api.routing.Router
import play.api.routing.SimpleRouter

// TODO extract in a library..?
object PlayfulRouter {
  type Routes = PartialFunction[(String, String), Handler]
}

trait PlayfulRouter extends SimpleRouter {

  def playfulRoutes: PlayfulRouter.Routes

  // adapt to match only on (method, URI)
  override def routes: Router.Routes =
    playfulRoutes.compose {
      case rh: RequestHeader => (rh.method, rh.uri)
    }

}
