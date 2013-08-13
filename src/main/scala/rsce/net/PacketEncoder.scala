package rsce.net

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandler.Sharable
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder

import rsce.valueobject.Packet

@Sharable
class PacketEncoder extends OneToOneEncoder {

  @throws(classOf[Exception])
  override protected def encode(ctx: ChannelHandlerContext, chan: Channel, o: Object) = {
    val p = o.asInstanceOf[Packet]
    if (p.opcode != -1) {
      // Short + Byte + payload's length
      val out = ChannelBuffers.directBuffer(2 + 1 + p.length)
      out.writeShort(p.length)
      out.writeByte(p.opcode)
      out.writeBytes(p.payload.toByteBuffer)
      out
    } else {
      val out = ChannelBuffers.directBuffer(p.length)
      out.writeBytes(p.payload.toByteBuffer)
      out
    }
  }

}
