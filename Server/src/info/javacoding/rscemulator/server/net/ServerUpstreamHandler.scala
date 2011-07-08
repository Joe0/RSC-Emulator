package info.javacoding.rscemulator.server.net
import org.jboss.netty.channel.SimpleChannelUpstreamHandler
import org.jboss.netty.channel.ChannelEvent
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ChannelStateEvent
import org.jboss.netty.channel.MessageEvent

/**
 * A SimpleChannleUpstreamHandler that dispatches messages to the corresponding class.
 * 
 * @author Joe Pritzel
 */
class ServerUpstreamHandler extends SimpleChannelUpstreamHandler {

  @throws(classOf[Exception])
  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    println(e)
  }
}