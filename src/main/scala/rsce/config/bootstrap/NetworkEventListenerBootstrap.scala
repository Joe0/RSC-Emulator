package rsce.config.bootstrap

import rsce.event.listener.{ ChannelConnectedEventListener, ChannelClosedEventListener }
import javax.inject.Named
import com.joepritzel.feather.PSBroker

class NetworkEventListenerBootstrap {

  def registerListeners(@Named("EventHandler") eh: PSBroker,
    connected: ChannelConnectedEventListener,
    closed: ChannelClosedEventListener) = {
    connected.subscribe(eh)
    closed.subscribe(eh)
  }

}