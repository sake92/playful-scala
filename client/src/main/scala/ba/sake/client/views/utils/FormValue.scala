package ba.sake.client.views.utils

import org.scalajs.dom

object FormValue {
  def fromEvent(target: dom.EventTarget): FormValue =
    new FormValue(target.asInstanceOf[dom.raw.HTMLFormElement])
}
final class FormValue(formElem: dom.raw.HTMLFormElement) {
  def inputValue(name: String): String = {
    formElem.elements.namedItem(name).asInstanceOf[dom.html.Input].value
  }
}
