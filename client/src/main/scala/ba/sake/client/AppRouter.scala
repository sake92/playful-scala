package ba.sake.client

import ba.sake.scalajs_router.Router
import ba.sake.shared.client.routes._
import ba.sake.client.views._, user._

class AppRouter {
  val router = Router()

  private val appContext = this

  private val routes: Router.Routes = {
    case "/"                   => MainComponent(appContext)
    case UserByIdRoute(userId) => UserComponent(userId)
    case UsersRoute()          => UsersListComponent(appContext)
  }

  router.withRoutesData(
    mountId = "main",
    routes = routes,
    notFoundComponent = MainComponent(appContext)
  ).init()

  def navigateTo(route: UserByIdRoute): Unit = {
    router.navigateTo(route.urlData.url)
  }

  def navigateTo(route: UsersRoute): Unit = {
    router.navigateTo(route.urlData.url)
  }
}
