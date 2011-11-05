package com.joepritzel.rsce.persistence

import com.joepritzel.rsce.persistence.entity._
import java.sql.Timestamp
import org.squeryl._
import org.squeryl.PrimitiveTypeMode._

/**
 * This object represents the database schema.
 * 
 * @author Joe Pritzel
 */
object Schema extends Schema {
	val players = table[PlayerData]("players")
	val stats = table[PlayerStats]("stats")
	val inventory = table[Inventory]("inventory")
	
	on(players)(p => declare(
		p.id is (unique, dbType("varchar(12)")),
		p.email is(dbType("varchar(256)")),
		p.passwordMD5 is(dbType("varchar(32)")),
		p.saltMD5 is(dbType("varchar(256)")),
		p.passwordSHA is(dbType("varchar(128)")),
		p.saltSHA is(dbType("varchar(256)")),
		p.lastIP is(dbType("varchar(15)")),
		p.lastIP defaultsTo("0.0.0.0"),
		p.x defaultsTo(777),
		p.y defaultsTo(777),
		p.groupID defaultsTo(0),
		p.loggedIn defaultsTo(false),
		p.chatBlock defaultsTo(false),
		p.privateBlock defaultsTo(false),
		p.tradeBlock defaultsTo(false),
		p.duelBlock defaultsTo(false),
		p.autoCamera defaultsTo(false),
		p.sound defaultsTo(false),
		p.oneMouseButton defaultsTo(false)
	))

	on(stats)(p => declare(
		p.id is (unique, dbType("varchar(12)")),
		p.hpExp defaultsTo(1200),
		p.hp defaultsTo(10)
	))
	
	on(inventory)(p => declare(
	    p.id is (dbType("varchar(12)"))
	    ))
}
