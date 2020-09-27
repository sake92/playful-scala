package ba.sake.client.views. user

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._
import org.scalajs.dom.raw.MouseEvent
import rescala.default._
import rescala.extra.Tags._
import ba.sake.scalajs_router.Component
import ba.sake.scalajs_router.Router
import ba.sake.client.services.UserService
import ba.sake.shared.api.models.user.User
import ba.sake.shared.client.routes.UserByIdRoute
import ba.sake.client.views.utils.Imports.Bundle._, Classes._
import ba.sake.client.AppRouter

case class UsersListComponent(appRouter: AppRouter) extends Component {

  private val users$ = Var(Vector.empty[User])

  UserService.getUsers().foreach { users =>
    users$.set(users.toVector)
  }

  override def asElement =
    div(
      h3("Users list"),
      table(tableClass, tableBordered, tableHoverable)(
        thead(
          tr(th("Username"), th("Email"), th())
        ),
        tbody(
          users$.map { users =>
            users.map { user =>
              tr(
                td(user.username),
                td(user.email),
                td(button(
                  onclick := { (e: MouseEvent) =>
                    appRouter.navigateTo(UserByIdRoute(user.id)())
                  },
                  btnClass
                )("Edit"))
              )
            }
          }.asModifierL
        )
      )
    ).render

}
