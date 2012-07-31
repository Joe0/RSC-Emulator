package rsce.config.bootstrap

import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import java.util.concurrent.Executors
import rsce.net.PacketEncoder
import rsce.net.PacketDecoder
import javax.inject.Inject
import org.jboss.netty.channel.ChannelFactory
import org.jboss.netty.channel.ChannelPipelineFactory
import org.jboss.netty.channel.Channels
import rsce.net.PacketHandler
import org.jboss.netty.handler.execution.ExecutionHandler
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor

class NetworkingBootstrap {

  def bootstrap {

    val channelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool, Executors.newCachedThreadPool)

    val bootstrap = new ServerBootstrap(channelFactory)

    val pipeline = new ChannelPipelineFactory {
      override def getPipeline = {
        Channels.pipeline(new PacketEncoder(), new PacketDecoder, new PacketHandler,
          new ExecutionHandler(new OrderedMemoryAwareThreadPoolExecutor(
            4, 0, 0)))
      }
    }

    bootstrap.setPipelineFactory(pipeline);
  }
}