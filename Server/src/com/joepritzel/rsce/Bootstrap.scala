package com.joepritzel.rsce

import event.EventManager
import persistence.Persistence
import java.net.InetSocketAddress
import java.util.concurrent.Executors
import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import org.jboss.netty.channel.{ ChannelPipeline, ChannelPipelineFactory, Channels }
import model.World
import net.CustomPipelineFactory

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

    // Networking
    Logger.info("Setting up networking")
    val factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool, Executors.newCachedThreadPool)
    bootstrap = new ServerBootstrap(factory)
    bootstrap.setPipelineFactory(new CustomPipelineFactory)
    bootstrap.setOption("child.tcpNoDelay", true)
    bootstrap.setOption("child.keepAlive", true)
    bootstrap.setOption("child.reuseAddress", true)
    bootstrap.setOption("child.connectTimeoutMillis", 30000)
    bootstrap.setOption("readWriteFair", true)
    bootstrap.bind(new InetSocketAddress(43595))
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      override def run {
        println("Shutting down");
        stop
        factory.releaseExternalResources();
      }
    }));
  }

  def stop {
    // Don't use Logger in here because it might/will already be shut down.
    World.dispose
    println("World saved")
    bootstrap.releaseExternalResources
    println("Networking stopped")
  }
}