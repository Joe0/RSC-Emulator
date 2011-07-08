package info.javacoding.rscemulator.server.net

import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import java.util.concurrent.Executors
import org.jboss.netty.channel.ChannelPipelineFactory
import org.jboss.netty.channel.ChannelPipeline
import org.jboss.netty.channel.Channels
import info.javacoding.rscemulator.server.net.codec.ServerEncoder
import info.javacoding.rscemulator.server.net.codec.ServerDecoder
import java.net.InetSocketAddress
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.MessageEvent

/**
 * An object whose sole purpose is to start the server.
 *
 * @author Joe Pritzel
 */
object Bootstrap {
  def init {
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