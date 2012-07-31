package rsce.net

import org.jboss.netty.channel.{ SimpleChannelUpstreamHandler, MessageEvent, ChannelStateEvent, ChannelHandlerContext }
import rsce.core.event.EventHandler
import rsce.entity.World
import rsce.event.{ EventDecodingFailed, ChannelConnectedEvent, ChannelClosedEvent }
import rsce.service.PacketDecodingService
import rsce.valueobject.Packet
import javax.inject.Inject

class PacketHandler extends SimpleChannelUpstreamHandler {

  @Inject private var world : World = null
  @Inject private var eventHandler : EventHandler = null

  @throws(classOf[Exception])
  override def messageReceived(ctx : ChannelHandlerContext, e : MessageEvent) {
    val packet = e.getMessage().asInstanceOf[Packet]
    val entity = world.getNetworkedEntity(e.getChannel.getId)
    PacketDecodingService.decode(entity, packet) match {
      case Some(event) => eventHandler.dispatch(event)
      case None        => eventHandler.dispatch(EventDecodingFailed(entity, packet))
    }
    super.messageReceived(ctx, e)
  }

  @throws(classOf[Exception])
  override def channelConnected(ctx : ChannelHandlerContext, e : ChannelStateEvent) {
    eventHandler.dispatch(ChannelConnectedEvent(e.getChannel))
    super.channelConnected(ctx, e)
  }

  @throws(classOf[Exception])
  override def channelClosed(ctx : ChannelHandlerContext, e : ChannelStateEvent) {
    eventHandler.dispatch(ChannelClosedEvent(e.getChannel))
    super.channelClosed(ctx, e)
  }
}
