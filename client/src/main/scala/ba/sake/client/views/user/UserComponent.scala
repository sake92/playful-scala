package ba.sake.client.views
package user

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._
import rescala.default._
import rescala.extra.Tags._
import ba.sake.scalajs_router.Component
import ba.sake.client.services.UserService
import ba.sake.shared.api.models.user.User
import utils.Imports.Bundle._, Classes._

case class UserComponent(userId: Long) extends Component {

  private val user$ = Var.empty[User]

  UserService.getUser(userId).foreach { user =>
    user.map(user$.set) // TODO popup error if not found..
  }

  override def asElement = {
    div(
      h3("User details"),
      user$.map { user =>
        div(b(user.username), s" lives in ${user.address}")
      }.asModifier
    ).render
  }

}
