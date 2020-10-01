package ba.sake.server.services

import ba.sake.shared.api.models.user._

class UserService {

  private var userIdCounter = 0
  private var users = List(
    User(getUserId(), "Sake", "sake@example.com"),
    User(getUserId(), "Meho", "meho@example.com"),
    User(getUserId(), "Hamo", "hamo@example.com")
  )

  def findAll(): Seq[User] = users

  def findById(userId: Long): Option[User] = users.find(_.id == userId)

  def create(req: CreateOrUpdateUserRequest): User = {
    val newUser = User(getUserId(), req.username, req.email, req.langs)
    users = users.appended(newUser)
    newUser
  }

  def update(userId: Long, req: CreateOrUpdateUserRequest): User = {
    val idx = users.indexWhere(_.id == userId)
    val updatedUser = users(idx).copy(username = req.username, email = req.email, langs = req.langs)
    users = users.updated(idx, updatedUser)
    updatedUser
  }

  def delete(userId: Long): Unit = {
    users = users.filterNot(_.id == userId)
  }

  private def getUserId(): Long = {
    userIdCounter += 1
    userIdCounter
  }
}
