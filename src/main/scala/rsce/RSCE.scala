package rsce

import com.google.inject.Guice
import rsce.config.bootstrap.Bootstraps

object RSCE {

  def main(args : Array[String]) {

    var injector = Guice.createInjector(new RSCEModule)

    injector.getInstance(classOf[Bootstraps])
  }

}