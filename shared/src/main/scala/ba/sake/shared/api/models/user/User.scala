package ba.sake.shared.api.models.user

case class User(id: Long, username: String, address: String)

object User {
  import play.api.libs.json.Json

  implicit val userFormat = Json.format[User]
}
