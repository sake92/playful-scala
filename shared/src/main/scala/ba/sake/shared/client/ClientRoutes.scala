package ba.sake.shared.client

import ba.sake.stone.Route

object routes {

  @Route class UserEditRoute(path1: "users", val userId: Long)
  @Route class UserCreateRoute(path1: "users", path2: "new")
  @Route class UsersRoute(path1: "users")
}
