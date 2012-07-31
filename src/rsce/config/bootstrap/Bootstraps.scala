package rsce.config.bootstrap
import rsce.core.event.EventHandler
import rsce.event.listener.{ ChannelConnectedEventListener, ChannelClosedEventListener }
import javax.inject.Inject

class Bootstraps {

  @Inject def bootstrap(eh : EventHandler, connected : ChannelConnectedEventListener, closed : ChannelClosedEventListener) {
    new NetworkingBootstrap().bootstrap
    new NetworkEventListenerBootstrap().registerListeners(eh, connected, closed)
  }
}