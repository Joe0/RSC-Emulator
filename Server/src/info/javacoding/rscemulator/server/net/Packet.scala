package info.javacoding.rscemulator.server.net
import org.jboss.netty.buffer.ChannelBuffer

/**
 * A class to wrap packets.
 *
 * @author Joe Pritzel
 */
class Packet(opcode_ : Int, length_ : Int, payload_ : ChannelBuffer, raw_ : Boolean = false) {
  def opcode = opcode_
  def length = length_
  def payload = payload_
  def isRaw = raw_
}