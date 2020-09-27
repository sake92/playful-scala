package ba.sake.client.views.utils

import ba.sake.hepek.bootstrap3.BootstrapBundle
import ba.sake.hepek.bootstrap3.component.BootstrapFormComponents

object Imports {

  val Bundle = {
    val bundle = BootstrapBundle()
    val newForm = bundle.Form.withFormType(BootstrapFormComponents.Type.Horizontal())
    bundle.withForm(newForm)
  }

}
