package com.joepritzel.rsce.event.impl
import com.joepritzel.rsce.util.RSA
import java.security.SecureRandom

/**
 * This is the first packet that is sent when a client tries to connect.<br>
 * Opcode 32 should conform to the RSCA client, and all others should change to use RSA securely.
 *
 * @author Joe Pritzel
 */
object SessionOpen extends Event[Packet] {

  private val random = new SecureRandom

  override def fire(p: Packet) {
    val player = p.player
    val payload = p.payload
    if (player.initialized()) {
      Logger.warning("Player already initialized.")
      return
    }

    val nameToLongRightShift16And31 = payload.readByte
    player.nameTestValue := nameToLongRightShift16And31
    val bytes = new Array[Byte](payload.readableBytes)
    payload.readBytes(bytes)
    val className = new String(bytes)
    player.clientMainClassName := className

    if (rsca(p))
      return

    // The way it should be done
    val keys = RSA.generateKeyPair
    player.publicKey := keys._1
    player.privateKey := keys._2
    val pb = new PacketBuilder()
    pb.buffer().writeInt(keys._1.getEncoded.length)
    pb.buffer().writeBytes(keys._1.getEncoded)
    player.channel().write(pb.toPacket)

  }

  def rsca(p: Packet) = {
    if (p.opcode == 32) {
      val serverKey = random.nextLong
      p.player.serverKey := serverKey
      val pb = new PacketBuilder()
      pb.buffer().writeLong(serverKey)
      p.player.channel().write(pb.toPacket)
      true
    }
    false
  }
}
