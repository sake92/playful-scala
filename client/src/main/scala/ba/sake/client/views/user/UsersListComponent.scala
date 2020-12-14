package ba.sake.client.views.user

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom
import scalatags.JsDom.all._
import ba.sake.rxtags._
import ba.sake.scalajs_router.Component
import ba.sake.scalajs_router.Router
import ba.sake.shared.api.models.user.User
import ba.sake.shared.api.routes.UserByIdRoute
import ba.sake.shared.client.routes._
import ba.sake.client.services.UserService
import ba.sake.client.views.utils.Imports._, Bundle._, Classes._

case class UsersListComponent(router: Router) extends Component {

  private val users$ = Var(Vector.empty[User])

  UserService.getUsers().foreach { users =>
    users$.set(users.toVector)
  }

  override def asElement =
    div(
      h3("Users list"),
      table(tableClass, tableBordered, tableHoverable)(
        thead(
          tr(th("Username"), th("Email"), th("Languages"), th())
        ),
        tbody(
          users$.map2 { user =>
            tr(
              td(user.username),
              td(user.email),
              td(user.langs.mkString(", ")),
              td(
                button(
                  btnClass,
                  onclick := { (e: dom.MouseEvent) => router.navigateTo(UserEditRoute(user.id)) }
                )("Edit"),
                button(
                  btnClass,
                  btnDanger,
                  onclick := { (e: dom.MouseEvent) => deleteUser(user) }
                )("Delete")
              )
            )

          }
        )
      ),
      button(
        btnClass,
        btnPrimary,
        onclick := { (e: dom.MouseEvent) => router.navigateTo(UserCreateRoute()) }
      )("Create")
    ).render

  private def deleteUser(user: User) = {
    UserService.delete(UserByIdRoute(user.id)).map { _ =>
      users$.set(_.filterNot(_ == user))
    }
  }
}
