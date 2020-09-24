package ba.sake.client.views

import scalatags.JsDom.all._
import ba.sake.scalajs_router.Component
import ba.sake.client.views.user.UsersComponent

object MainComponent extends Component {

  override def asElement = {
    UsersComponent.asElement
  }
}
