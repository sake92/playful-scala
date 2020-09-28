package ba.sake.server.routing

import play.api.mvc.Handler
import play.api.mvc.RequestHeader
import play.api.routing.Router
import play.api.routing.SimpleRouter


// TODO extract in a library..?
object PlayfulRouter {
  type Routes = PartialFunction[(String, String), Handler]
}

trait PlayfulRouter extends SimpleRouter with PlayfulConsts {

  def playfulRoutes: PlayfulRouter.Routes

  // adapt to match only on (method, URI)
  override def routes: Router.Routes = playfulRoutes.compose { case rh: RequestHeader => (rh.method, rh.uri) }
}

private[routing] trait PlayfulConsts {

  final val GET: String = "GET"
  final val POST: String = "POST"
  final val PUT: String = "PUT"
  final val DELETE: String = "DELETE"
}
