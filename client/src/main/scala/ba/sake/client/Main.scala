package ba.sake.client

import org.scalajs.dom.document
import ba.sake.scalajs_router.Router
import ba.sake.shared.client.routes._
import ba.sake.client.views._, user._

object Main extends App {

  val router = Router()

  val routes: Router.Routes = {
    case "/"                   => MainComponent(router)
    case UserByIdRoute(userId) => UserComponent(userId)
  }

  router.withRoutesData(
    mountId = "main",
    routes = routes,
    notFoundComponent = MainComponent(router)
  ).init()

}
