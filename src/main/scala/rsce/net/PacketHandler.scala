package rsce.net

import org.jboss.netty.channel.{ SimpleChannelUpstreamHandler, MessageEvent, ChannelStateEvent, ChannelHandlerContext }
import rsce.entity.World
import rsce.event.{ EventDecodingFailed, ChannelConnectedEvent, ChannelClosedEvent }
import rsce.service.PacketDecodingService
import rsce.valueobject.Packet
import javax.inject.Inject
import com.joepritzel.feather.PSBroker
import javax.inject.Named

class PacketHandler extends SimpleChannelUpstreamHandler {

  @Inject private var world: World = null
  
  @Inject
  @Named("EventHandler")
  private var eventHandler: PSBroker = null

  @throws(classOf[Exception])
  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    val packet = e.getMessage().asInstanceOf[Packet]
    val entity = world.getNetworkedEntity(e.getChannel.getId)
    PacketDecodingService.decode(entity, packet) match {
      case Some(event) => eventHandler.publish(event)
      case None => eventHandler.publish(EventDecodingFailed(entity, packet))
    }
    super.messageReceived(ctx, e)
  }

  @throws(classOf[Exception])
  override def channelConnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    eventHandler.publish(ChannelConnectedEvent(e.getChannel))
    super.channelConnected(ctx, e)
  }

  @throws(classOf[Exception])
  override def channelClosed(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    eventHandler.publish(ChannelClosedEvent(e.getChannel))
    super.channelClosed(ctx, e)
  }
}
