package ba.sake.client

import ba.sake.scalajs_router.Router
import ba.sake.shared.client.routes._
import ba.sake.client.views._, user._
import ba.sake.stone.RouteImpl

class AppRouter {
  val router = Router()

  private val appRouter = this

  private val routes: Router.Routes = {
    case "/"                   => MainComponent(appRouter)
    case UserEditRoute(userId) => UserComponent(appRouter, Some(userId))
    case UserCreateRoute()     => UserComponent(appRouter, None)
    case UsersRoute()          => UsersListComponent(appRouter)
  }

  router.withRoutesData(
    mountId = "main",
    routes = routes,
    notFoundComponent = MainComponent(appRouter)
  ).init()

  def navigateTo(route: RouteImpl): Unit = router.navigateTo(route.urlData.url)

}
