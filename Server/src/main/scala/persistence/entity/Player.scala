package com.joepritzel.rsce.persistence.entity

import java.sql.Timestamp
import org.squeryl.KeyedEntity

class Player(name_ : String, email_ : String, passwordMD5_ : String, saltMD5_ : String, passwordSHA_ : String, saltSHA_ : String, x_ : Int,
 y_ : Int, group_ : Int, subscriptionTime_ : Timestamp, lastIP_ : String, loggedIn_ : Boolean, chatBlock_ : Boolean, privateBlock_ : Boolean,
	 tradeBlock_ : Boolean, duelBlock_ : Boolean, autoCamera_ : Boolean, sound_ : Boolean, oneMouseButton_ : Boolean) extends KeyedEntity[String] {
	val id = name_
	val email = email_
	val passwordMD5 = passwordMD5_
	val saltMD5 = saltMD5_
	val passwordSHA = passwordSHA_
	val saltSHA = saltSHA_
	val x = x_
	val y = y_
	val groupID = group_

	var subscriptionTime = subscriptionTime_
	var lastIP = lastIP_
	var loggedIn = loggedIn_
	var chatBlock = chatBlock_
	var privateBlock = privateBlock_
	var tradeBlock = tradeBlock_
	var duelBlock = duelBlock_
	var autoCamera = autoCamera_
	var sound = sound_
	var oneMouseButton = oneMouseButton_	
}
