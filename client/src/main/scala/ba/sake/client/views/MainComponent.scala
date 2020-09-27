package ba.sake.client.views

import scalatags.JsDom.all._
import ba.sake.scalajs_router.Component
import ba.sake.client.AppRouter
import ba.sake.client.views.user.UsersListComponent

case class MainComponent(appRouter: AppRouter) extends Component {

  override def asElement = {
    UsersListComponent(appRouter).asElement
  }
}
