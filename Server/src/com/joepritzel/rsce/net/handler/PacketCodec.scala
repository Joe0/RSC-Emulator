package com.joepritzel.rsce.net.handler
import com.joepritzel.rsce.net.Packet
import org.jboss.netty.buffer.{ ChannelBuffer, ChannelBuffers }
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext }
import org.jboss.netty.handler.codec.frame.FrameDecoder
import java.util.concurrent.ConcurrentHashMap
import com.joepritzel.rsce.model.Player
import org.jboss.netty.channel.ChannelUpstreamHandler
import org.jboss.netty.channel.ChannelDownstreamHandler
import org.jboss.netty.channel.ChannelHandler.Sharable
import org.jboss.netty.channel.MessageEvent
import org.jboss.netty.channel.ChannelEvent
import org.jboss.netty.channel.Channels
import com.joepritzel.rsce.model.World

/**
 * A class that encodes and decodes packets sent by the client.
 *
 * @author Joe Pritzel
 */
@Sharable
class PacketCodec extends ChannelUpstreamHandler with ChannelDownstreamHandler {

  private def decode(ctx: ChannelHandlerContext, buf: ChannelBuffer) = {
    if (buf.readableBytes < 2) {
      null
    }
    buf.markReaderIndex()
    val len = buf.readShort

    if (buf.readableBytes < len) {
      buf.resetReaderIndex
      null
    }

    val opcode = buf.readByte & 0xFF
    val payload = ChannelBuffers.dynamicBuffer
    payload.writeBytes(buf.readBytes(len - 1))
    val p = new Packet(opcode, len - 1, payload)
    val pl = World.getPlayerById(ctx.getChannel.getId)
    if (pl != null)
      p.player = pl
    p
  }

  private def encode(ctx: ChannelHandlerContext, packet: Packet) = {
    val out = ChannelBuffers.buffer(packet.payload.writerIndex)
    if (!packet.isRaw) {
      out.writeByte((packet.length << 8).toByte)
      out.writeByte(packet.length.toByte)
      out.writeByte(packet.opcode.toByte)
    }

    out.writeBytes(packet.payload)
    out
  }

  @throws(classOf[Exception])
  override protected def handleUpstream(ctx: ChannelHandlerContext, ce: ChannelEvent) {
    if (!ce.isInstanceOf[MessageEvent]) {
      ctx.sendUpstream(ce)
      return
    }

    val me = ce.asInstanceOf[MessageEvent]
    if (!me.getMessage.isInstanceOf[ChannelBuffer]) {
      ctx.sendUpstream(ce)
      return
    }

    Channels.fireMessageReceived(ctx,
      decode(ctx, me.getMessage.asInstanceOf[ChannelBuffer]))
  }

  @throws(classOf[Exception])
  override protected def handleDownstream(ctx: ChannelHandlerContext, ce: ChannelEvent) {
    if (!ce.isInstanceOf[MessageEvent]) {
      ctx.sendDownstream(ce)
      return
    }

    val me = ce.asInstanceOf[MessageEvent]
    if (!me.getMessage.isInstanceOf[Packet]) {
      ctx.sendDownstream(ce)
      return
    }

    Channels.write(ctx, me.getFuture, encode(ctx, me.getMessage.asInstanceOf[Packet]))
  }

}