package com.joepritzel.rsce.net
import com.joepritzel.rsce.event.EventManager
import com.joepritzel.rsce.model.{ Player, World }
import org.jboss.netty.channel.{ ChannelHandlerContext, ChannelStateEvent, ExceptionEvent, MessageEvent, SimpleChannelUpstreamHandler }
import org.jboss.netty.channel.ChannelHandler.Sharable

/**
 * A SimpleChannleUpstreamHandler that passes messages to the EventManager, and handles new connections.
 *
 * @author Joe Pritzel
 */
@Sharable
class ServerUpstreamHandler extends SimpleChannelUpstreamHandler {

  @throws(classOf[Exception])
  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    val packet = e.getMessage.asInstanceOf[Packet]
    packet.player = World.getPlayerById(e.getChannel.getId)
    EventManager.process(packet)
  }

  @throws(classOf[Exception])
  override def channelConnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    val p = new Player
    p.channel := e.getChannel
    World.addPlayer(p)
  }

  @throws(classOf[Exception])
  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) {
    //World.getPlayerById(e.getChannel.getId).dispose
    //e.getChannel.close
    e.getCause.printStackTrace
  }
}