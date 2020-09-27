package ba.sake.shared.api

import ba.sake.stone.Route

object routes {

  @Route class UsersRoute(api: "api", path1: "users")()
  @Route class UserByIdRoute(api: "api", path1: "users", val userId: Long)()

  @Route class AssetRoute(path1: "assets", val filePath: "*")()

}
