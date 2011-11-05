package com.joepritzel.rsce.model
import com.joepritzel.rsce.persistence.entity.{ PlayerData, Inventory, PlayerStats }
import java.security.{ PrivateKey, PublicKey }
import java.sql.Timestamp
import org.jboss.netty.channel.Channel

/**
 * This class represents a player.
 *
 * @author Joe Pritzel
 */
class Player extends Entity {
  def dispose {
    channel().close
    save
  }

  val channel = new Property[Channel](null)

  val initialized = new Property[Boolean](false)

  val nameTestValue = new Property[Int](Integer.MIN_VALUE)

  val clientMainClassName = new Property[String](null)

  val publicKey = new Property[PublicKey](null)

  val privateKey = new Property[PrivateKey](null)

  val serverKey = new Property[Long](0L)

  val playerData = new Property[PlayerData](null)

  val inventory = new Property[Inventory](null)
  
  val stats = new Property[PlayerStats](null)

  def load(user: String, password: String, reconnecting: Boolean) = {
    import com.joepritzel.rsce.util.Hash
    // TODO: Load info from a database, and then send the information retrieved.
    val salts = PlayerData.getSalts(user)
    val passwordMD5 = Hash.md5(password, salts._1)
    val passwordSHA = Hash.sha512(password, salts._2)
    val entry = PlayerData.load(user, passwordMD5, passwordSHA)
    if (entry == null) {
      false
    } else {
      playerData := entry
      true
    }
  }

  def save {
    PlayerData.save(playerData())
    Inventory.save(inventory())
    PlayerStats.save(stats())
  }
}
