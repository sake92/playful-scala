package ba.sake.client.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom.ext.Ajax
import play.api.libs.json.Json
import ba.sake.shared.api.routes._
import ba.sake.shared.api.models.user.User
import ba.sake.shared.api.models.user.CreateOrUpdateUserRequest

object UserService {

  def getUsers(): Future[Seq[User]] = {
    val url = UsersRoute().urlData.url
    Ajax.get(url).map { xhr =>
      val parsedJson = Json.parse(xhr.responseText)
      Json.fromJson[Seq[User]](parsedJson).get // TODO handle errors
    }
  }

  def getUser(route: UserByIdRoute): Future[Option[User]] = {
    val url = route.urlData.url
    Ajax.get(url).map { xhr =>
      val parsedJson = Json.parse(xhr.responseText)
      Json.fromJson[User](parsedJson).asOpt
    }
  }

  def create(createUserReq: CreateOrUpdateUserRequest): Future[User] = {
    val url = UsersRoute().urlData.url
    val reqJson = Json.stringify(Json.toJson(createUserReq))
    val headers = Map("Content-Type" -> "application/json")
    Ajax.post(url, reqJson, headers = headers).map { xhr =>
      val parsedJson = Json.parse(xhr.responseText)
      Json.fromJson[User](parsedJson).get
    }
  }

  def update(route: UserByIdRoute, updateUserReq: CreateOrUpdateUserRequest): Future[User] = {
    val url = route.urlData.url
    val reqJson = Json.stringify(Json.toJson(updateUserReq))
    val headers = Map("Content-Type" -> "application/json")
    Ajax.put(url, reqJson, headers = headers).map { xhr =>
      val parsedJson = Json.parse(xhr.responseText)
      Json.fromJson[User](parsedJson).get
    }
  }
}
