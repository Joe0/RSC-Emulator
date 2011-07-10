package com.joepritzel.rsce.model
import java.security.{ PrivateKey, PublicKey }
import org.jboss.netty.channel.Channel

/**
 * This class represents a player.
 *
 * @author Joe Pritzel
 */
class Player extends Entity {
  def dispose {
    channel().close
  }

  val channel = new Property[Channel](null)

  val name = new Property[String](null)

  val initialized = new Property[Boolean](false)

  val nameTestValue = new Property[Int](Integer.MIN_VALUE)

  val clientMainClassName = new Property[String](null)

  val publicKey = new Property[PublicKey](null)

  val privateKey = new Property[PrivateKey](null)

  val serverKey = new Property[Long](0L)

  def load(info: Tuple3[String, String, Boolean], hashed: Boolean) {
    // TODO: Load info from a database, and then send the information retrieved.
  }
}