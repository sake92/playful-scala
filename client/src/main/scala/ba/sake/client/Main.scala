package ba.sake.client

import ba.sake.shared.api.routes.UserByIdRoute

object Main extends App {

  val url = UserByIdRoute(123)().urlData.url
  
  println("URL = " + url)
}
