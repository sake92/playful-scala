package ba.sake.client

import ba.sake.scalajs_router.Router
import ba.sake.shared.client.routes._
import ba.sake.client.views._, user._
import ba.sake.stone.RouteImpl

object Main extends App {

  var router = Router()

  val routes: Router.Routes = {

    case UserEditRoute(userId) => UserComponent(router, Some(userId))
    case UserCreateRoute()     => UserComponent(router, None)
    case UsersRoute()          => UsersListComponent(router)
    case _                     => MainComponent(router)
  }

  router = router.withRoutesData(
    mountId = "main",
    routes = routes
  )

  router.init()

}
