package ba.sake.client.views.utils

import ba.sake.hepek.bootstrap3.BootstrapBundle
import ba.sake.hepek.bootstrap3.component.BootstrapFormComponents
import ba.sake.scalajs_router.Router
import ba.sake.stone.RouteImpl

object Imports {

  val Bundle = locally {
    val bundle = BootstrapBundle()
    val newForm = bundle.Form.withFormType(BootstrapFormComponents.Type.Horizontal())
    bundle.withForm(newForm)
  }

  implicit class RouterOps(router: Router) {
    def navigateTo(route: RouteImpl): Unit = router.navigateTo(route.urlData.url)
  }

}
