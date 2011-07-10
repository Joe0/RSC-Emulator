package info.javacoding.rscemulator.server.event.impl
import info.javacoding.rscemulator.server.event.Event
import info.javacoding.rscemulator.server.log.Logger
import info.javacoding.rscemulator.server.net.{ Packet, PacketBuilder }
import info.javacoding.rscemulator.server.util.RSA
import java.security.SecureRandom

/**
 * This is the first packet that is sent when a client tries to connect.<br>
 * Opcode 32 should conform to the RSCA client, and all others should change to use RSA securely.
 *
 * @author Joe Pritzel
 */
object SessionOpen extends Event {

  override def act {
    loop {
      react {
        case p: Packet => {
          sender ! false
          handle(p)
        }
      }
    }
  }

  private val random = new SecureRandom

  private def handle(p: Packet) {
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

    if (p.opcode == 32) {
      val serverKey = random.nextLong
      player.serverKey := serverKey
      val pb = new PacketBuilder()
      pb.buffer().writeLong(serverKey)
      player.channel().write(pb.toPacket)
    } else {
      val keys = RSA.generateKeyPair
      player.publicKey := keys._1
      player.privateKey := keys._2
      val pb = new PacketBuilder()
      pb.buffer().writeInt(keys._1.getEncoded.length)
      pb.buffer().writeBytes(keys._1.getEncoded)
      player.channel().write(pb.toPacket)
    }
  }
}