package rsce.event.listener

import javax.inject.Inject
import rsce.core.event.EventHandler
import rsce.entity.{ World, Player }
import rsce.event.{ ChannelConnectedEvent, ChannelClosedEvent }
import com.google.common.eventbus.Subscribe

class NetworkEventListenerBootstrap(world : World) {
  @Inject var eh : EventHandler = null
  eh.registerListeners(new ChannelConnectedEventListener(world), new ChannelClosedEventListener(world))
}

class ChannelConnectedEventListener(world : World) {
  @Subscribe def connect(event : ChannelConnectedEvent) {
    // Assume player for now
    @Inject var entity : Player = null
    entity.setChannel(event.channel)
    world.addNetworkedEntity(entity)
  }
}

class ChannelClosedEventListener(world : World) {
  @Subscribe def close(event : ChannelClosedEvent) {
    world.removeNetworkedEntity(event.channel.getId)
  }
}