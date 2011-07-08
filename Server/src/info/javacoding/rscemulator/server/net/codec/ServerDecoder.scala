package info.javacoding.rscemulator.server.net.codec
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.handler.codec.frame.FrameDecoder
import org.jboss.netty.buffer.ChannelBuffers
import info.javacoding.rscemulator.server.net.Packet

/**
 * A class that decodes packets sent by the RSC client.
 *
 * @author Joe Pritzel
 */
class ServerDecoder extends FrameDecoder {

  @throws(classOf[Exception])
  override protected def decode(ctx: ChannelHandlerContext, channel: Channel, buf: ChannelBuffer): Object = {
    if (buf.readableBytes < 2) {
      null
    }
    buf.markReaderIndex()
    val lenA = (buf.readByte, buf.readByte)
    val len = (((lenA._1 & 0xFF) << 8).toShort | (lenA._2 & 0xFF).toShort)

    if (buf.readableBytes < len) {
      buf.resetReaderIndex
      null
    }

    val opcode = buf.readByte & 0xFF
    val payload = ChannelBuffers.dynamicBuffer
    payload.writeBytes(buf.readBytes(len - 1))
    new Packet(opcode, len - 1, payload)
  }

}