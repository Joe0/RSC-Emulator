package com.joepritzel.rsce.net
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.Channel
import com.joepritzel.rsce.model.Player
import scala.ref.WeakReference

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

  private var _player: WeakReference[Player] = null
  def player_=(player: Player) = _player = new WeakReference[Player](player)
  def player = {
    _player.get match {
      case Some(x) => x
      case None => null
    }
  }
}