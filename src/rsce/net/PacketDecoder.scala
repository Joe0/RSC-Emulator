package rsce.net;

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.{ChannelHandlerContext, Channel}
import org.jboss.netty.handler.codec.replay.ReplayingDecoder

import rsce.valueobject.{Payload, Packet}

class PacketDecoder extends ReplayingDecoder[States](States.READ_LENGTH) {

  private var length : Int = -1
  private var opcode : Byte = -1

  @throws(classOf[Exception])
  override protected def decode(ctx : ChannelHandlerContext, chan : Channel,
                                buf : ChannelBuffer, state : States) : Packet = {
    go(buf, state)
  }

  private def go(buf : ChannelBuffer, state : States) : Packet = {
    state match {
      case States.READ_LENGTH =>
        length = buf.readShort
        checkpoint(States.READ_OPCODE)
        return go(buf, state)
      case States.READ_OPCODE =>
        opcode = buf.readByte
        checkpoint(States.READ_PAYLOAD)
        return go(buf, state)
      case States.READ_PAYLOAD =>
        val p = buf.readBytes(length - 1)
        return new Packet(opcode, length - 1, new Payload(p.toByteBuffer))
    }

  }

}
