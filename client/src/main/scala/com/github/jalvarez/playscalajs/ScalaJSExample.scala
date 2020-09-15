package com.github.jalvarez.playscalajs

import org.scalajs.dom
import ba.sake.shared.ServerRoutes.UserByIdRoute

object ScalaJSExample {
  def main(args: Array[String]): Unit = {
    //dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    val url = UserByIdRoute("123").urlData.url
    println("URL = " + url)

  }
}
