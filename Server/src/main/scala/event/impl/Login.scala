package com.joepritzel.rsce.event.impl
import com.joepritzel.rsce.event.Event
import com.joepritzel.rsce.model.World
import com.joepritzel.rsce.net.{ Packet, PacketBuilder }
import com.joepritzel.rsce.util.{ InsecureRSA, RSA }
import com.joepritzel.rsce.Config
import org.jboss.netty.buffer.ChannelBuffers

/**
 * The second packet that is sent from the client, that tries to actually log the user in.<br>
 * Opcode 77 is compatible with the RSCA client, and all other opcodes should conform to a secure usage of RSA.
 *
 * @author Joe Pritzel
 */
object Login extends Event {
  override def act {
    loop {
      react {
        case p: Packet => {
          sender ! false
          var payload = p.payload
          val player = p.player
          var responseCode = 0.toByte

          var info: (String, String, Boolean) = null

          try {
            val data = {
              val bytes = new Array[Byte](payload.readableBytes)
              payload.readBytes(bytes)
              if (p.opcode == 77)
                InsecureRSA.decrypt(bytes)
              else
                RSA.decrypt(bytes, p.player.privateKey())
            }

            payload = ChannelBuffers.dynamicBuffer
            payload.writeBytes(data)

            val reconnecting = (payload.readByte == 1)
            val version = payload.readInt

            // These are useless, number generated from (int) (Math.random() * 99999999D)
            if (p.opcode == 77) {
              payload.readInt; payload.readInt
            }

            // These are the keys we just sent from SessionOpen
            val originalKey77 = {
              if (p.opcode == 77) {
                payload.readLong
              } else {
                -1L
              }
            }

            val keyBytes = {
              if (p.opcode != 77) {
                val bytes = new Array[Byte](payload.readInt)
                payload.readBytes(bytes)
                bytes
              } else {
                null
              }
            }

            val username = {
              val bytes = new Array[Byte](20)
              payload.readBytes(bytes)
              new String(bytes).trim
            }

            val password = {
              if (p.opcode == 77) {
                val bytes = new Array[Byte](20)
                payload.readBytes(bytes)
                new String(bytes).trim
              } else {
                val bytes = new Array[Byte](32)
                payload.readBytes(bytes)
                new String(bytes)
              }
            }

            if (Config.maxPlayers != 0 && World.playerCount >= Config.maxPlayers)
              responseCode = 10

            if ((p.opcode == 77 && originalKey77 != player.serverKey()) || {
              var ret = false
              if (keyBytes != null) {
                val orig = player.publicKey().getEncoded
                try {
                  for (i <- 0 until orig.length; if (orig(i) != keyBytes(i))) {
                    ret = true
                    true
                  }
                } catch {
                  case _ => ret = true
                }
              }
              ret
            }) {
              responseCode = 5
              Logger.warning("Key mismatch - username = " + username)
            }

            if (Config.versionProtection && Config.version != version) {
              responseCode = 4
              Logger.warning("Client version mismatch - " + Config.version + " != " + version + " username = " + username)
            }

            if (responseCode == 0) {
              info = (username, password, reconnecting)
            }
          } catch {
            case e: Exception => {
              responseCode = 5
              e.printStackTrace
            }
          }

          val pb = new PacketBuilder()
          pb.buffer().writeByte(responseCode)
          player.channel().write(pb.toPacket)
          if (responseCode != 0)
            World.removePlayer(player)
          else
            player.load(info, p.opcode != 77)
        }
      }
    }
  }
}
