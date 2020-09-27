package ba.sake.client.views
package user

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom
import scalatags.JsDom.all._
import rescala.default._
import rescala.extra.Tags._
import ba.sake.scalajs_router.Component
import ba.sake.shared.api.models.user.User
import ba.sake.client.services.UserService
import utils._
import Imports.Bundle._, Classes._

case class UserComponent(userId: Long) extends Component {

  private val user$ = Var.empty[User]

  UserService.getUser(userId).foreach { user =>
    user.map(user$.set)
  }

  override def asElement = {
    import Form._
    div(
      h3("Edit User:"),
      user$.map { user =>
        div(
          form(onsubmit := (sendData _))(
            inputText(value := user.username)("username", "Username"),
            inputEmail(value := user.email)("email", "Email fgfdgdf"),
            inputSubmit()("Submit")
          )
        )
      }.asModifier
    ).render
  }

  private def sendData(e: dom.Event) = {
    e.preventDefault()

    val formValue = FormValue.fromEvent(e.target)
    val username = formValue.inputValue("username")
    val email = formValue.inputValue("email")
    println(User(0, username, email))
  }
}
