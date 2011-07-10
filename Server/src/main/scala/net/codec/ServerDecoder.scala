package com.joepritzel.rsce.net.codec
import com.joepritzel.rsce.net.Packet
import org.jboss.netty.buffer.{ ChannelBuffer, ChannelBuffers }
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext }
import org.jboss.netty.handler.codec.frame.FrameDecoder
import java.util.concurrent.ConcurrentHashMap
import com.joepritzel.rsce.model.Player

/**
 * A class that decodes packets sent by the RSC client.
 *
 * @author Joe Pritzel
 */
class ServerDecoder extends FrameDecoder {

  private val players = new ConcurrentHashMap[Int, Player]

  @throws(classOf[Exception])
  override protected def decode(ctx: ChannelHandlerContext, channel: Channel, buf: ChannelBuffer): Object = {
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
    val pl = players.get(channel.getId)
    if (pl != null)
      p.player = pl
    p
  }

}