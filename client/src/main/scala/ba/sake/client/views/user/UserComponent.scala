package ba.sake.client.views.user

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom
import rescala.default._
import rescala.extra.Tags._
import scalatags.JsDom.all._
import ba.sake.scalajs_router.Component
import ba.sake.shared.api.models.user.User
import ba.sake.shared.api.models.user.CreateOrUpdateUserRequest
import ba.sake.client.AppRouter
import ba.sake.client.services.UserService
import ba.sake.client.views.utils._
import Imports.Bundle._, Classes._

case class UserComponent(appRouter: AppRouter, maybeUserId: Option[Long]) extends Component {

  private val user$ = Var(User(1, "", ""))

  private val actionName = maybeUserId.map(_ => "Edit").getOrElse("Create")

  maybeUserId.foreach { userId =>
    UserService.getUser(userId).foreach { user =>
      user.map(user$.set)
    }
  }

  override def asElement = {
    import Form._
    div(
      h3(s"$actionName User:"),
      br,
      user$.map { user =>
        div(
          form(onsubmit := (sendData _))(
            inputText(value := user.username)("username", "Username"),
            inputEmail(value := user.email)("email", "Email"),
            inputSubmit()("Submit")
          )
        )
      }.asModifier
    ).render
  }

  private def sendData(e: dom.Event): Unit = {
    e.preventDefault()

    val formValues = FormValues.fromEvent(e.target)
    val username = formValues.getValue("username").trim
    val email = formValues.getValue("email").trim
    if (username.isEmpty) return

    val user = CreateOrUpdateUserRequest(username, email)
    val futureRes = maybeUserId match {
      case Some(userId) => UserService.update(userId, user)
      case None         => UserService.create(user)
    }

    futureRes.map { user =>
      appRouter.router.navigateTo("/")
    }
  }
}
