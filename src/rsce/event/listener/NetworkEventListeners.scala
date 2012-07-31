package rsce.event.listener

import javax.inject.Inject
import rsce.entity.{ World, Player }
import rsce.event.{ ChannelConnectedEvent, ChannelClosedEvent }
import com.google.common.eventbus.Subscribe
import com.google.inject.Provider

class ChannelConnectedEventListener @Inject() (world : World, provider : Provider[Player]) {

  @Subscribe def connect(event : ChannelConnectedEvent) {
    val entity = provider.get
    entity.setChannel(event.channel)
    world.addNetworkedEntity(entity)
  }
}

class ChannelClosedEventListener {
  @Inject private var world : World = null
  @Subscribe def close(event : ChannelClosedEvent) {
    world.removeNetworkedEntity(event.channel.getId)
  }
}