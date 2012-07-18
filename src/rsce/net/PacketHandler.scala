package rsce.net

import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ChannelStateEvent
import org.jboss.netty.channel.MessageEvent
import org.jboss.netty.channel.SimpleChannelUpstreamHandler
import rsce.core.event.EventHandler
import rsce.entity.Player
import rsce.entity.World
import rsce.event._
import rsce.valueobject.Packet
import rsce.service.PacketDecodingService
import rsce.event.EventDecodingFailed
import rsce.event.ChannelConnectedEvent

class PacketHandler extends SimpleChannelUpstreamHandler {

  private lazy val eventHandler = World.injector.getInstance(classOf[EventHandler])

  @throws(classOf[Exception])
  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    val packet = e.getMessage().asInstanceOf[Packet]
    val entity = World.getNetworkedEntity(e.getChannel.getId)
    PacketDecodingService.decode(entity, packet) match {
      case Some(event) => eventHandler.dispatch(event)
      case None => eventHandler.dispatch(EventDecodingFailed(entity, packet))
    }
    super.messageReceived(ctx, e)
  }

  @throws(classOf[Exception])
  override def channelConnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    eventHandler.dispatch(ChannelConnectedEvent(e.getChannel))
    super.channelConnected(ctx, e)
  }

  @throws(classOf[Exception])
  override def channelClosed(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    eventHandler.dispatch(ChannelClosedEvent(e.getChannel))
    super.channelClosed(ctx, e)
  }
}
