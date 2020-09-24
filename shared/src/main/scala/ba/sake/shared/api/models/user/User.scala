package ba.sake.shared.api.models.user

import play.api.libs.json.Json

case class User(id: Long, username: String, address: String)

object User {
  implicit val userFormat = Json.format[User]
}

case class CreateUserRequest(username: String, address: String)

object CreateUserRequest {
  implicit val createUserRequestFormat = Json.format[CreateUserRequest]
}