package ba.sake.server.views

import controllers.Assets
import scalatags.Text.all._
import ba.sake.server.views.utils.Imports.Bundle._

case class IndexPage(val assets: Assets) extends HtmlPage {

  val customGrid = Grid.withScreenRatios(
    Grid.screenRatios.withAll(
      Grid.screenRatios.lg.withSingle(1, 3, 1)
    )
  )
  import customGrid._

  override def siteSettings =
    super.siteSettings
      .withName("example.com")
      .withFaviconNormal("favicon.png")

  override def pageSettings = super.pageSettings.withTitle("Home page")

  override def pageContent =
    div(
      Navbar.simple(
        brandUrl = "/",
        brandIconUrl = Some("/assets/images/favicon.png"),
        left = List(hyperlink("/")("Home"))
      ),
      row(
        """
          ## Opa
          Selam, merhaba! :)
        """.md
      )
    )

  override def scriptURLs =
    super.scriptURLs.appended("/assets/client-fastopt.js")

  override def styleURLs =
    super.styleURLs.appended("/assets/stylesheets/main.css")
}
