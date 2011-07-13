package com.joepritzel.rsce.persistence.entity

import java.sql.Timestamp
import org.squeryl.KeyedEntity

/**
 * This class stores the data for players.
 * 
 * @author Joe Pritzel
 */
class PlayerData(name_ : String, email_ : String, passwordMD5_ : String, saltMD5_ : String, passwordSHA_ : String, saltSHA_ : String, x_ : Int,
 y_ : Int, group_ : Int, subscriptionTime_ : Timestamp, lastIP_ : String, loggedIn_ : Boolean, chatBlock_ : Boolean, privateBlock_ : Boolean,
	 tradeBlock_ : Boolean, duelBlock_ : Boolean, autoCamera_ : Boolean, sound_ : Boolean, oneMouseButton_ : Boolean) extends KeyedEntity[String] {

	/**
	 * The name of the player.
	 */
	var id = name_

	/**
	 * The player's e-mail address.
	 */
	var email = email_

	/**
	 * The name MD5 hash of the salt prepended to the player's password.
	 */
	var passwordMD5 = passwordMD5_

	/**
	 * The salt that is prepended to the player's password when hashed with MD5.
	 */
	var saltMD5 = saltMD5_

	/**
	 * The name SHA-512 hash of the salt prepended to the player's password.
	 */
	var passwordSHA = passwordSHA_

	/**
	 * The salt that is prepended to the player's password when hashed with SHA-512.
	 */
	var saltSHA = saltSHA_

	/**
	 * The group that the player belongs to.
	 */
	var groupID = group_

	/**
	 * The player's x coordinate.
	 */
	var x = x_

	/**
	 * The player's y coordinate.
	 */
	var y = y_

	/**
	 * A timestamp of when the player's subscription will expire.
	 */
	var subscriptionTime = subscriptionTime_

	/**
	 * The last I.P. address that the player used.
	 */
	var lastIP = lastIP_

	/**
	 * If true, the player is logged in.
	 */
	var loggedIn = loggedIn_

	/**
	 * If true chat is blocked.
	 */
	var chatBlock = chatBlock_

	/**
	 * If true, private chat is blocked.
	 */
	var privateBlock = privateBlock_

	/**
	 * If true, trade requests are blocked.
	 */
	var tradeBlock = tradeBlock_

	/**
	 * If true, duel requests are blocked.
	 */
	var duelBlock = duelBlock_

	/**
	 * If true, the camera is set to automatically adjust.
	 */
	var autoCamera = autoCamera_

	/**
	 * If true, sounds are enabled.
	 */
	var sound = sound_

	/**
	 * If true, one mouse button mode is enabled.
	 */
	var oneMouseButton = oneMouseButton_	

}

/**
 * This object is for all transactions and queries involving player data. 
 * 
 * @author Joe Pritzel
 */
object PlayerData {
	import com.joepritzel.rsce.model.Player
	import com.joepritzel.rsce.persistence._
	import org.squeryl.PrimitiveTypeMode._
	import org.squeryl.Query

	def getSalts(user: String) = {
		var result: (String, String) = null
		transaction {
			result = from(Schema.players)(p => where(p.id === user) select(p.saltMD5, p.saltSHA)).single
		}
		result
	}

	/**
	 * Returns a single player that has a matching id, MD5 hash, and SHA-512 hash.
	 */
	def loadPlayer(user: String, passwordMD5: String, passwordSHA: String) = {
		var result: PlayerData = null
		
		transaction {
			try {
				result = Schema.players.where(p => p.id === user and p.passwordMD5 === passwordMD5 and p.passwordSHA === passwordSHA).single
			} catch {
				case _ => result = null		
			}
		}
		
		result
	}

	/**
	 * Updates a player's record using the one provided.
	 */
	def savePlayer(p: PlayerData) {
		transaction {
			Schema.players.update(p)
		}
	}
}
