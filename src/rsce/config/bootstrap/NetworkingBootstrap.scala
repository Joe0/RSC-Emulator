package rsce.config.bootstrap

import java.util.concurrent.Executors

import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.{Channels, ChannelPipelineFactory}
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import org.jboss.netty.handler.execution.{OrderedMemoryAwareThreadPoolExecutor, ExecutionHandler}

import rsce.net.{PacketHandler, PacketEncoder, PacketDecoder}

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