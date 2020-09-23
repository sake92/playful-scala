package ba.sake.client

import org.scalajs.dom.document
import ba.sake.client.views.MainComponent

object Main extends App {

  val mainElem = document.getElementById("main")
  mainElem.appendChild(MainComponent.asElement)
}
