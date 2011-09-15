package com.joepritzel.rsce

import com.joepritzel.rsce.event.EventDispatcher
import com.joepritzel.rsce.net.codec.{ ServerDecoder, ServerEncoder }
import com.joepritzel.rsce.persistence.Persistence
import java.net.InetSocketAddress
import java.util.concurrent.Executors
import net.ServerUpstreamHandler
import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import org.jboss.netty.channel.{ ChannelPipeline, ChannelPipelineFactory, Channels }
import com.joepritzel.rsce.model.World

/**
 * An object whose sole purpose is to start the server.
 *
 * @author Joe Pritzel
 */
object Bootstrap {
  private var bootstrap: ServerBootstrap = null

  def init {
    // Database
    Logger.info("Starting persistence layer")
    Persistence.init
  
    // Events
    Logger.info("Starting event dispatcher")
    EventDispatcher.start

    // Networking
    Logger.info("Setting up networking")
    bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool,
      Executors.newCachedThreadPool))
    bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

      @throws(classOf[Exception])
      override def getPipeline = {
        Channels.pipeline(new ServerEncoder, new ServerDecoder, new ServerUpstreamHandler)
      }
    })
    bootstrap.bind(new InetSocketAddress(43595))
  }

  def stop {
      EventDispatcher.stop
      println("Event dispatcher stopped")
      World.dispose
      println("Saving world")
      bootstrap.releaseExternalResources
      println("Networking stopped")
  }
}