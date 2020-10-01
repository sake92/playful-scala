package ba.sake.client.views.user

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom
import rescala.default._
import rescala.extra.Tags._
import scalatags.JsDom.all._
import ba.sake.scalajs_router.Component
import ba.sake.shared.api.models.user._
import ba.sake.shared.api.routes.UserByIdRoute
import ba.sake.client.AppRouter
import ba.sake.client.services.UserService
import ba.sake.client.views.utils._
import Imports.Bundle._, Classes._

case class UserComponent(appRouter: AppRouter, maybeUserId: Option[Long]) extends Component {

  private val user$ = Var(User(1, "", ""))

  private val actionName = maybeUserId.map(_ => "Edit").getOrElse("Create")

  maybeUserId.foreach { userId =>
    UserService.getUser(UserByIdRoute(userId)).foreach { user =>
      user.map(user$.set)
    }
  }

  override def asElement = {
    import Form._
    div(
      h3(s"$actionName User:"),
      br,
      div(
        form(onsubmit := (submitForm _))(
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
  private def updateUser(f: (User, String) => User): (dom.KeyboardEvent => Unit) =
    e => {
      val newValue = e.target.asInstanceOf[dom.html.Input].value
      user$.transform(u => f(u, newValue))
    }

  private def updateUserLang(pl: ProgLang.ProgLang): (dom.Event => Unit) =
    e => {
      val isChecked = e.target.asInstanceOf[dom.html.Input].checked
      user$.transform { u =>
        val newLangs =
          if (isChecked) u.langs.appended(pl)
          else u.langs.filterNot(_ == pl)
        u.copy(langs = newLangs)
      }
    }

  private def submitForm(e: dom.Event): Unit = {
    e.preventDefault()

    val user = user$.now
    val userReq = CreateOrUpdateUserRequest(user.username, user.email, user.langs)

    val futureRes = maybeUserId match {
      case Some(userId) => UserService.update(UserByIdRoute(userId), userReq)
      case None         => UserService.create(userReq)
    }

    futureRes.map { user =>
      appRouter.router.navigateTo("/")
    }
  }
}
