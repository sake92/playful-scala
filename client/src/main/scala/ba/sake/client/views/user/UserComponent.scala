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

  private val users$ = Var(Vector.empty[User])

  UserService.getUsers().foreach { users =>
    users$.set(users.toVector)
  }

  override def asElement = {

    div("Userrrrrrr " + userId).render
  }

}
