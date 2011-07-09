package info.javacoding.rscemulator.server.net.codec
import info.javacoding.rscemulator.server.net.Packet
import org.jboss.netty.buffer.{ ChannelBuffer, ChannelBuffers }
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext }
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder

/**
 * A class that encodes packets so the client can then decode them.
 *
 * @author Joe Pritzel
 */
class ServerEncoder extends OneToOneEncoder {
  @throws(classOf[Exception])
  override protected def encode(ctx: ChannelHandlerContext, channel: Channel, msg: Object): Object = {
    val packet = msg.asInstanceOf[Packet]
    val out = ChannelBuffers.buffer(packet.payload.writerIndex)
    if (!packet.isRaw) {
      out.writeByte((packet.length << 8).toByte)
      out.writeByte(packet.length.toByte)
      out.writeByte(packet.opcode.toByte)
    }

    out.writeBytes(packet.payload)
    out
  }
}