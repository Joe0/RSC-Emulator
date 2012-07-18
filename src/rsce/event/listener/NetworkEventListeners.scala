package rsce.event.listener

import javax.inject.Inject
import rsce.core.event.EventHandler
import rsce.entity.{World, Player}
import rsce.event.{ChannelConnectedEvent, ChannelClosedEvent}

object NetworkEventListenerBootstrap {
  val eh = World.injector.getInstance(classOf[EventHandler])
  eh.registerListeners(new ChannelConnectedEventListener, new ChannelClosedEventListener)
}

class ChannelConnectedEventListener {
  @Inject
  def connect(event : ChannelConnectedEvent) {
    // Assume player for now
    val entity = World.injector.getInstance(classOf[Player])
    entity.setChannel(event.channel)
    World.addNetworkedEntity(entity)
  }
}

class ChannelClosedEventListener {
  @Inject
  def close(event : ChannelClosedEvent) {
    World.removeNetworkedEntity(event.channel.getId)
  }
}