package rsce

import com.google.inject.AbstractModule
import rsce.entity.World
import rsce.entity.Player
import rsce.entity.PlayerProvider
import com.google.inject.name.Names
import com.joepritzel.feather.PSBrokerBuilder
import com.joepritzel.feather.strategy.publish.Sequential
import com.joepritzel.feather.PSBroker
import com.joepritzel.feather.strategy.publish.FewQuickListeners
import java.util.concurrent.Executors

/**
 * This serves as the configuration for all general aspects of RSCE.
 *
 * @author Joe Pritzel
 *
 */
class RSCEModule extends AbstractModule {
  override protected def configure {
    bind(classOf[World]).toInstance(new World)

    bind(classOf[Player]).toProvider(classOf[PlayerProvider])

    bind(classOf[PSBroker]).annotatedWith(Names.named("EventHandler")).toInstance(
      new PSBrokerBuilder().publishStrategy(new Sequential(true)).build)
      
    bind(classOf[PSBroker]).annotatedWith(Names.named("IOHandler")).toInstance(
      new PSBrokerBuilder().publishStrategy(new FewQuickListeners(
        Executors.newCachedThreadPool, true)).build)

  }
}