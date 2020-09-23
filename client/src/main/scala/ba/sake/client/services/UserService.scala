package ba.sake.client.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom.ext.Ajax
import play.api.libs.json.Json
import ba.sake.shared.api.routes.UsersRoute
import ba.sake.shared.api.models.user.User

object UserService {

  def getUsers(): Future[Seq[User]] = {
    val url = UsersRoute()().urlData.url
    Ajax.get(url).map { xhr =>
      val parsedJson = Json.parse(xhr.responseText)
      Json.fromJson[Seq[User]](parsedJson).get // TODO handle errors
    }
  }
}
