package ba.sake.client

import ba.sake.scalajs_router.Router
import ba.sake.shared.client.routes._
import ba.sake.client.views._, user._

class AppRouter {
  val router = Router()

  private val appContext = this

  private val routes: Router.Routes = {
    case "/"                   => MainComponent(appContext)
    case UserEditRoute(userId) => UserComponent(appContext, Some(userId))
    case UserCreateRoute()     => UserComponent(appContext, None)
    case UsersRoute()          => UsersListComponent(appContext)
  }

  router.withRoutesData(
    mountId = "main",
    routes = routes,
    notFoundComponent = MainComponent(appContext)
  ).init()

  def navigateTo(route: UserEditRoute): Unit = {
    router.navigateTo(route.urlData.url)
  }

  def navigateTo(route: UserCreateRoute): Unit = {
    router.navigateTo(route.urlData.url)
  }

  def navigateTo(route: UsersRoute): Unit = {
    router.navigateTo(route.urlData.url)
  }
}
