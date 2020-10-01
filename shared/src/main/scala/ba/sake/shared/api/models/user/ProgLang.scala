package ba.sake.shared.api.models.user

import play.api.libs.json._

object ProgLang extends Enumeration {
  type ProgLang = Value
  val SCALA, JAVA, JS = Value

  implicit val progLangReads = Reads.enumNameReads(ProgLang)
  implicit val progLangWrites = Writes.enumNameWrites
}
