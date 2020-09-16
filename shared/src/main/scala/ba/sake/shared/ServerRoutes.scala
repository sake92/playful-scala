package ba.sake.shared

import ba.sake.stone.Route

object ServerRoutes {

  @Route class UserByIdRoute(path1: "users", val userId: Long)()

  @Route class BlogPostByIdRoute(path1: "posts", val blogPostId: Long)()

  // TODO nemere jer file matcha samo 1 subpath, a ne više njih...
  @Route class AssetRoute(path1: "assets", val file: String)()
 
}
