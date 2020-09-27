package ba.sake.client.views.utils

import org.scalajs.dom

object FormValues {
  def fromEvent(target: dom.EventTarget): FormValues =
    new FormValues(target.asInstanceOf[dom.raw.HTMLFormElement])
}

final class FormValues(formElem: dom.raw.HTMLFormElement) {

  def getValue(name: String): String = {
    formElem.elements.namedItem(name).asInstanceOf[dom.html.Input].value
  }
}
