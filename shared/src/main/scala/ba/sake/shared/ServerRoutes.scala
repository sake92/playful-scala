package ba.sake.shared

import ba.sake.stone.Route

object ServerRoutes {

  @Route class UserByIdRoute(path1: "users", val id: String)()
}
