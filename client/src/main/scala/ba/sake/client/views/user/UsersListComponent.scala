package ba.sake.client.views.user

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom
import rescala.default._
import rescala.extra.Tags._
import scalatags.JsDom.all._
import ba.sake.scalajs_router.Component
import ba.sake.shared.api.models.user.User
import ba.sake.shared.client.routes._
import ba.sake.client.AppRouter
import ba.sake.client.services.UserService
import ba.sake.client.views.utils.Imports.Bundle._, Classes._

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
                td(
                  button(
                    btnClass,
                    onclick := { (e: dom.MouseEvent) => appRouter.navigateTo(UserEditRoute(user.id)) }
                  )("Edit")
                )
              )
            }
          }.asModifierL
        )
      ),
      button(
        btnClass,
        btnPrimary,
        onclick := { (e: dom.MouseEvent) => appRouter.navigateTo(UserCreateRoute()) }
      )("Create")
    ).render

}
