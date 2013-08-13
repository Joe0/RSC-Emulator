package rsce.config.bootstrap
import rsce.event.listener.{ ChannelConnectedEventListener, ChannelClosedEventListener }
import javax.inject.Inject
import com.joepritzel.feather.PSBroker

class Bootstraps {

  @Inject def bootstrap(eh: PSBroker, connected: ChannelConnectedEventListener, closed: ChannelClosedEventListener) {
    new NetworkingBootstrap().bootstrap
    new NetworkEventListenerBootstrap().registerListeners(eh, connected, closed)
  }
}