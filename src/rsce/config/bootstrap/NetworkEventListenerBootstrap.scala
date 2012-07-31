package rsce.config.bootstrap

import rsce.core.event.EventHandler
import rsce.event.listener.{ChannelConnectedEventListener, ChannelClosedEventListener}

class NetworkEventListenerBootstrap {

  def registerListeners(
    eh : EventHandler,
    connected : ChannelConnectedEventListener,
    closed : ChannelClosedEventListener) = eh.registerListeners(connected, closed)

}