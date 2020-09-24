package ba.sake.client.views
package user

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._
import rescala.default._
import rescala.extra.Tags._
import ba.sake.scalajs_router.Component
import ba.sake.client.services.UserService
import ba.sake.shared.api.models.user.User
import ba.sake.shared.api.routes.UserByIdRoute
import utils.Imports.Bundle._, Classes._

object UsersComponent extends Component {

  private val users$ = Var(Vector.empty[User])

  UserService.getUsers().foreach { users =>
    users$.set(users.toVector)
  }

  override def asElement = {
    table(tableClass, tableBordered, tableHoverable)(
      thead(
        tr(th("Username"), th("Address"), th())
      ),
      tbody(
        users$.map { users =>
          users.map { user =>
            tr(
              td(user.username),
              td(user.address),
              td(a(href := UserByIdRoute(user.id)().urlData.url)("Edit"))
            )
          }
        }.asModifierL
      )
    ).render
  }

}
