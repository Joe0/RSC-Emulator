package com.joepritzel.rsce

import com.joepritzel.rsce.event.EventDispatcher
import com.joepritzel.rsce.net.codec.{ServerDecoder, ServerEncoder}
import java.net.InetSocketAddress
import java.util.concurrent.Executors
import net.ServerUpstreamHandler
import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import org.jboss.netty.channel.{ChannelPipeline, ChannelPipelineFactory, Channels}

/**
 * An object whose sole purpose is to start the server.
 *
 * @author Joe Pritzel
 */
object Bootstrap {
  def init {
    EventDispatcher.start
    val bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool,
      Executors.newCachedThreadPool))
    bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

      @throws(classOf[Exception])
      override def getPipeline = {
        Channels.pipeline(new ServerEncoder, new ServerDecoder, new ServerUpstreamHandler)
      }
    })
    bootstrap.bind(new InetSocketAddress(43595))
  }
}