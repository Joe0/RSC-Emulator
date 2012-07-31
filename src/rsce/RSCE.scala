package rsce

import rsce.entity.World
import com.google.inject.Guice
import rsce.module._
import rsce.core.event.EventHandler
import rsce.module.Bootstraps

object RSCE {
  def main(args : Array[String]) = {
    var injector = Guice.createInjector(new RSCEModule)

    val networkingModule = new NetworkingModule(injector.getInstance(classOf[World]), injector.getInstance(classOf[EventHandler]))

    injector = injector.createChildInjector(networkingModule)

    new Bootstraps().bootstrap
  }
}