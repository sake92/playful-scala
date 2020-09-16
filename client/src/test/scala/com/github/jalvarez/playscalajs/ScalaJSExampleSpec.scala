package ba.sake.client

import org.scalajs.dom
import org.scalatest.flatspec.AnyFlatSpec

class ScalaJSExampleSpec extends AnyFlatSpec {

  it should "should show shared message" in new DomPlaceholder {
    ScalaJSExample.main(Array.empty[String])
    //assert(dom.document.getElementById("scalajsShoutOut").textContent == SharedMessages.itWorks)
  }

  trait DomPlaceholder {
    val newNode = dom.document.createElement("div")
    newNode.setAttribute("id", "scalajsShoutOut")
    dom.document.body.appendChild(newNode)
  }
}
