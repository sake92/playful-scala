package ba.sake.shared.api.models.user

import play.api.libs.json._

case class User(id: Long, username: String, email: String, langs: Seq[ProgLang.ProgLang] = Seq.empty)

object User {
  implicit val userFormat: Format[User] = Json.format[User]
}

case class CreateOrUpdateUserReq(
    username: String,
    email: String,
    langs: Seq[ProgLang.ProgLang] = Seq.empty
)

object CreateOrUpdateUserReq {
  implicit val cuUserReqFormat: Format[CreateOrUpdateUserReq] = Json.format[CreateOrUpdateUserReq]
}
