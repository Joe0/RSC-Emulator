package com.joepritzel.rsce.net.handler
import org.jboss.netty.channel.ChannelHandler.Sharable
import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.ChannelEvent
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ChannelStateEvent
import org.jboss.netty.channel.MessageEvent

/**
 * This class is used to log 'important' ChannelEvents.
 *
 * @author Joe Pritzel
 */
@Sharable
class LogHandler extends SimpleChannelHandler {
  @throws(classOf[Exception])
  override def handleUpstream(ctx: ChannelHandlerContext, e: ChannelEvent) {
    if (e.isInstanceOf[ChannelStateEvent])
      Logger.info("Channel state changed: " + e)
  }

  @throws(classOf[Exception])
  override def handleDownstream(ctx: ChannelHandlerContext, e: ChannelEvent) {
    if (e.isInstanceOf[MessageEvent])
      Logger.info("Writing: " + e)
  }
}