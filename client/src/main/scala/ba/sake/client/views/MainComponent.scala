package ba.sake.client.views

import scalatags.JsDom.all._
import ba.sake.scalajs_router.Component
import ba.sake.scalajs_router.Router
import ba.sake.client.views.user.UsersListComponent

case class MainComponent(router: Router) extends Component {

  override def asElement = {
    UsersListComponent(router).asElement
  }
}
