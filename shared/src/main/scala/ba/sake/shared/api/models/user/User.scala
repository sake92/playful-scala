package ba.sake.shared.api.models.user

import play.api.libs.json.Json

case class User(id: Long, username: String, email: String)

object User {
  implicit val userFormat = Json.format[User]
}

case class CreateOrUpdateUserRequest(username: String, email: String)

object CreateOrUpdateUserRequest {
  implicit val createOrUpdateUserRequestFormat = Json.format[CreateOrUpdateUserRequest]
}
