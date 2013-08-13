package rsce.event.listener

import javax.inject.Inject
import rsce.entity.{ World, Player }
import rsce.event.{ ChannelConnectedEvent, ChannelClosedEvent }
import com.google.inject.Provider
import com.joepritzel.feather.Subscriber
import com.joepritzel.feather.PSBroker

class ChannelConnectedEventListener @Inject() (world: World, provider: Provider[Player]) extends Subscriber[ChannelConnectedEvent] {

  override def receive(event: ChannelConnectedEvent) {
    val entity = provider.get
    entity.setChannel(event.channel)
    world.addNetworkedEntity(entity)
  }

  def subscribe(broker: PSBroker) = {
    broker.subscribe(this, classOf[ChannelConnectedEvent])
  }
}

class ChannelClosedEventListener extends Subscriber[ChannelClosedEvent] {
  @Inject private var world: World = null

  override def receive(event: ChannelClosedEvent) {
    world.removeNetworkedEntity(event.channel.getId)
  }

  def subscribe(broker: PSBroker) = {
    broker.subscribe(this, classOf[ChannelClosedEvent])
  }

}