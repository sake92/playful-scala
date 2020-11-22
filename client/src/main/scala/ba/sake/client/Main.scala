package ba.sake.client

import ba.sake.scalajs_router.Router
import ba.sake.shared.client.routes._
import ba.sake.client.views._, user._
import ba.sake.stone.RouteImpl

object Main extends App {

  var router = Router()

  val mainComponent = MainComponent(router)
  private val routes: Router.Routes = {
    case "/"                   => mainComponent
    case UserEditRoute(userId) => UserComponent(router, Some(userId))
    case UserCreateRoute()     => UserComponent(router, None)
    case UsersRoute()          => UsersListComponent(router)
  }

  router = router.withRoutesData(
    mountId = "main",
    routes = routes,
    notFoundComponent = mainComponent
  )

  router.init()

}
