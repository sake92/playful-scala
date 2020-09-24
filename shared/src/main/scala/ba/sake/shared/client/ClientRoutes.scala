package ba.sake.shared.client

import ba.sake.stone.Route

object routes {

  @Route class UsersRoute(path1: "users")()
  @Route class UserByIdRoute(path1: "users", val userId: Long)()

}
