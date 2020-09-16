package ba.sake.server.views

import ba.sake.server.views.utils.Imports.Bundle._

object IndexPage extends HtmlPage {

  override def siteSettings =
    super.siteSettings
      .withName("example.com")
      .withFaviconNormal("favicon.png")

  override def pageSettings = super.pageSettings.withTitle("Home page")
}
