package ba.sake.server.controllers

import play.api.mvc.Results._
import play.api.mvc.DefaultActionBuilder
import ba.sake.server.routing.PlayfulRouter
import ba.sake.shared.api.routes.BlogPostByIdRoute

class BlogPostController(val Action: DefaultActionBuilder) extends PlayfulRouter {

  override val playfulRoutes = {
    case (GET, BlogPostByIdRoute(blogPostId)) =>
      Action {
        Ok(s"GET BlogPost by id = $blogPostId")
      }
  }
}
