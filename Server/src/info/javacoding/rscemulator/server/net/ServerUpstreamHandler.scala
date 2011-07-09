package info.javacoding.rscemulator.server.net
import info.javacoding.rscemulator.server.event.EventDispatcher
import info.javacoding.rscemulator.server.model.{ Player, World }
import org.jboss.netty.channel.{ ChannelHandlerContext, ChannelStateEvent, ExceptionEvent, MessageEvent, SimpleChannelUpstreamHandler }

/**
 * A SimpleChannleUpstreamHandler that dispatches messages to the corresponding class.
 *
 * @author Joe Pritzel
 */
class ServerUpstreamHandler extends SimpleChannelUpstreamHandler {

  @throws(classOf[Exception])
  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    e.getMessage.asInstanceOf[Packet].player = World.getPlayerById(e.getChannel.getId)
    EventDispatcher ! e.getMessage
  }

  @throws(classOf[Exception])
  override def channelConnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    val p = new Player
    p.channel := e.getChannel
    World.addPlayer(p)
  }

  @throws(classOf[Exception])
  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) {
    World.getPlayerById(e.getChannel.getId).dispose
    e.getChannel.close
  }
}