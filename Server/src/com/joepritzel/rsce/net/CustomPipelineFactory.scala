package com.joepritzel.rsce.net
import org.jboss.netty.channel.ChannelHandler
import org.jboss.netty.channel.ChannelPipelineFactory
import com.joepritzel.rsce.net.handler.PacketCodec
import com.joepritzel.rsce.net.handler.LogHandler
import org.jboss.netty.channel.Channels
import com.joepritzel.rsce.Config
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import org.jboss.netty.handler.execution.ExecutionHandler

class CustomPipelineFactory extends ChannelPipelineFactory {
  val packetCodec = new PacketCodec
  val serverHandler = new ServerUpstreamHandler
  val logger = new LogHandler
  val pipelineExecutor = new OrderedMemoryAwareThreadPoolExecutor(
    Config.maxChannelExecutorThreads, Config.maxChannelMemorySize, Config.maxTotalMemorySize, Config.channelExecutorThreadKeepAliveTime, TimeUnit.MILLISECONDS, Executors
      .defaultThreadFactory());

  @throws(classOf[Exception])
  override def getPipeline = {
    val pipeline = Channels.pipeline
    pipeline.addLast("packet_codec", packetCodec)
    pipeline.addLast("server_handler", serverHandler)
    pipeline.addLast("pipeline_executor", new ExecutionHandler(pipelineExecutor))
    if (Config.logPackets)
      pipeline.addLast("logger", logger)
    pipeline
  }
}