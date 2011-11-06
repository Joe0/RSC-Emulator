package com.joepritzel.rsce.net
import org.jboss.netty.channel.ChannelHandler
import org.jboss.netty.channel.ChannelPipelineFactory
import com.joepritzel.rsce.net.handler.PacketCodec
import com.joepritzel.rsce.net.handler.LogHandler
import org.jboss.netty.channel.Channels
import com.joepritzel.rsce.Config

class CustomPipelineFactory extends ChannelPipelineFactory {
  val packetCodec = new PacketCodec
  val serverHandler = new ServerUpstreamHandler
  val logger = new LogHandler

  @throws(classOf[Exception])
  override def getPipeline = {
    val pipeline = Channels.pipeline
    pipeline.addLast("packet_codec", packetCodec)
    pipeline.addLast("server_handler", serverHandler)
    if (Config.logPackets)
      pipeline.addLast("logger", logger)
    pipeline
  }
}