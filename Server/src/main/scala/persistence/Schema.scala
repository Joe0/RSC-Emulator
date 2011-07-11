package com.joepritzel.rsce.persistence

import org.squeryl._
import org.squeryl.PrimitiveTypeMode._
import com.joepritzel.rsce.persistence.entity._

object Schema extends Schema {
	val players = table[Player]("players")
	
	on(players)(p => declare(
		p.id is (unique, dbType("varchar(12)")),
		p.email is(dbType("varchar(256)")),
		p.passwordMD5 is(dbType("varchar(32)")),
		p.saltMD5 is(dbType("varchar(256)")),
		p.passwordSHA is(dbType("varchar(128)")),
		p.saltSHA is(dbType("varchar(256)")),
		p.x is(dbType("integer")),
		p.y is(dbType("integer")),
		p.groupID is(dbType("integer")),
		p.subscriptionTime is(dbType("datetime")),
		p.lastIP is(dbType("varchar(15)")),
		p.loggedIn is(dbType("bool")),
		p.chatBlock is(dbType("bool")),
		p.privateBlock is(dbType("bool")),
		p.tradeBlock is(dbType("bool")),
		p.duelBlock is(dbType("bool")),
		p.autoCamera is(dbType("bool")),
		p.sound is(dbType("bool")),
		p.oneMouseButton is(dbType("bool"))
	))
}
