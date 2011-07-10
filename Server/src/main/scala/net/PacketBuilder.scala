package com.joepritzel.rsce.net
import org.jboss.netty.buffer.{ ChannelBuffer, ChannelBuffers }

/**
 * This class is used to build packets.
 *
 * @author Joe Pritzel
 */
class PacketBuilder(opcode: Int = -1) {
  val buffer = new Property[ChannelBuffer](ChannelBuffers.dynamicBuffer)
  def toPacket = {
    new Packet(opcode, buffer().capacity, buffer(), true)
  }
}