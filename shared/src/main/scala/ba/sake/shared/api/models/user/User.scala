package ba.sake.shared.api.models.user

import play.api.libs.json._

case class User(id: Long, username: String, email: String, langs: Seq[ProgLang.ProgLang] = Seq.empty)

object User {
  implicit val userFormat: Format[User] = Json.format[User]
}

case class CreateOrUpdateUserRequest(username: String, email: String, langs: Seq[ProgLang.ProgLang])

object CreateOrUpdateUserRequest {
  implicit val cuUserReqFormat: Format[CreateOrUpdateUserRequest] = Json.format[CreateOrUpdateUserRequest]
}
