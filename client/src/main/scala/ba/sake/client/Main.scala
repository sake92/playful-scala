package ba.sake.client

import org.scalajs.dom.document
import ba.sake.scalajs_router.Router
import ba.sake.shared.client.routes._
import ba.sake.client.views._, user._

object Main extends App {

  val routes: Router.Routes = {
    case "/"                   => MainComponent
    case UserByIdRoute(userId) => UserComponent(userId)
  }

  Router(
    mountId = "main",
    routes = routes,
    notFoundComponent = MainComponent
  ).init()
  
}
