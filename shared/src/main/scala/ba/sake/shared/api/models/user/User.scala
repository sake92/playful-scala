package ba.sake.shared.api.models.user

import play.api.libs.json.Json

case class User(
  id: Long,
  username: String
)

object User {
  implicit val userFormat = Json.format[User]
}