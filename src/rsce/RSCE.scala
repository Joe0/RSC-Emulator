package rsce

import com.google.inject.Guice
import rsce.core.event.EventHandler
import rsce.entity.World
import rsce.config.module.RSCEModule
import rsce.config.bootstrap.Bootstraps
import javax.inject.Inject
import rsce.event.listener.ChannelConnectedEventListener

object RSCE {

  def main(args : Array[String]) {

    var injector = Guice.createInjector(new RSCEModule)

    injector.getInstance(classOf[Bootstraps])
  }

}