package ba.sake.server.views

import play.api.Mode
import scalatags.Text.all._
import utils.Imports.Bundle._

case class IndexPage(mode: Mode) extends HtmlPage {

  override def siteSettings = super.siteSettings.withName("example.com").withFaviconNormal("favicon.png")

  override def pageSettings = super.pageSettings.withTitle("Home page")

  /* content */
  val customGrid = Grid.withScreenRatios(
    Grid.screenRatios.withAll(Ratios().withSingle(1, 3, 1))
  )
  import customGrid._

  override def pageContent =
    div(
      Navbar.simple(
        brandUrl = "/",
        brandIconUrl = Some("/assets/images/favicon.png"),
        left = List(hyperlink("/")("Home"))
      ),
      row(
        s"""
          ## Opa
          Selam, merhaba! :)

          Running in **$mode mode**!
        """.md,
        div(id := "main") // ScalaJS here
      )
    )

  override def scriptURLs = {
    val scalaJsFile = if (mode == Mode.Dev) "fastopt" else "opt"
    super.scriptURLs.appended(s"/assets/client-$scalaJsFile.js")
  }

  override def styleURLs = super.styleURLs.appended("/assets/stylesheets/main.css")
}
