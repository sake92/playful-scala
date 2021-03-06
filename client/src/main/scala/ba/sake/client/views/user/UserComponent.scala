package ba.sake.client.views.user

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom
import ba.sake.rxtags._
import scalatags.JsDom.all._
import io.scalaland.chimney.dsl._
import ba.sake.scalajs_router.Component
import ba.sake.scalajs_router.Router
import ba.sake.shared.api.models.user._
import ba.sake.shared.api.routes.UserByIdRoute
import ba.sake.client.services.UserService
import ba.sake.client.views.utils._
import Imports.Bundle._, Classes._

case class UserComponent(router: Router, maybeUserId: Option[Long]) extends Component {
  import UserComponent.UserModel

  private val user$ = Var(UserModel("", ""))

  private val actionName = maybeUserId.map(_ => "Edit").getOrElse("Create")

  maybeUserId.foreach { userId =>
    UserService.getUser(UserByIdRoute(userId)).foreach { maybeUser =>
      maybeUser.map { user =>
        user$.set(user.transformInto[UserModel])
      }
    }
  }

  override def asElement = {
    import Form._
    div(
      h3(s"$actionName User:"),
      br,
      div(
        form(onsubmit := submitForm())(
          inputText(
            value := user$.map(_.username),
            onkeyup := updateUser((user, v) => user.copy(username = v))
          )("username", "Username"),
          inputEmail(
            value := user$.map(_.email),
            onkeyup := updateUser((user, v) => user.copy(email = v))
          )("email", "Email"),
          inputCheckboxes(
            "langs",
            ProgLang.values.toSeq.map { pl =>
              (
                pl.toString,
                pl.toString,
                Seq(
                  checked := user$.map { u =>
                    val isChecked = u.langs.exists(_ == pl)
                    Option.when(isChecked)("checked")
                  },
                  onchange := updateUserLang(pl)
                )
              )
            },
            _label = "Languages"
          ),
          inputSubmit()("Submit")
        )
      )
    ).render
  }

  // we return a Function here!
  private def updateUser(f: (UserModel, String) => UserModel): (dom.KeyboardEvent => Unit) =
    e => {
      val newValue = e.target.asInstanceOf[dom.html.Input].value
      user$.set(u => f(u, newValue))
    }

  private def updateUserLang(pl: ProgLang.ProgLang): (dom.Event => Unit) =
    e => {
      val isChecked = e.target.asInstanceOf[dom.html.Input].checked
      user$.set { u =>
        val newLangs =
          if (isChecked) u.langs.appended(pl)
          else u.langs.filterNot(_ == pl)
        u.copy(langs = newLangs)
      }
    }

  private def submitForm(): (dom.Event => Unit) =
    e => {
      e.preventDefault()

      val userReq = user$.now.transformInto[CreateOrUpdateUserReq]
      val futureRes = maybeUserId match {
        case Some(userId) => UserService.update(UserByIdRoute(userId), userReq)
        case None         => UserService.create(userReq)
      }

      futureRes.foreach { _ =>
        router.navigateTo("/")
      }
    }
}

object UserComponent {
  case class UserModel(username: String, email: String, langs: Seq[ProgLang.ProgLang] = Seq.empty)
}
