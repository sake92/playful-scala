package ba.sake.shared.api

import ba.sake.stone.Route

object routes {

  @Route class UserByIdRoute(path1: "users", val userId: Long)()

  @Route class BlogPostByIdRoute(path1: "posts", val blogPostId: Long)()

  @Route class AssetRoute(path1: "assets", val filePath: "*")()
 
}
