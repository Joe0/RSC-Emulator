package rsce.net;

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.{ ChannelHandlerContext, Channel }
import org.jboss.netty.handler.codec.replay.ReplayingDecoder
import rsce.valueobject.{ Payload, Packet }
import org.jboss.netty.handler.codec.frame.FrameDecoder

class PacketDecoder extends FrameDecoder {

  @throws(classOf[Exception])
  override protected def decode(ctx: ChannelHandlerContext, chan: Channel, buf: ChannelBuffer): Packet = {
    if (buf.readableBytes < 2) null
    buf.markReaderIndex
    val length = buf.readShort

    if (buf.readableBytes < length) {
      buf.resetReaderIndex
      null
    }

    val opcode = buf.readByte
    val p = buf.readBytes(length - 1)
    new Packet(opcode, length - 1, new Payload(p.toByteBuffer))
  }
}
