package com.joepritzel.rsce.persistence

import com.joepritzel.rsce.Config
import org.squeryl.{ SessionFactory, Session }
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.MySQLAdapter

/**
 * This object creates the session to the database, 
 * and creates the schema if it doesn't exist.
 * 
 * @author Joe Pritzel
 */
object Persistence {
	def init = {
		val hostAndDatabase = Config.DBHost + Config.DBName
		Class.forName("com.mysql.jdbc.Driver")
		SessionFactory.concreteFactory = Some(() => Session.create(
        java.sql.DriverManager.getConnection(hostAndDatabase, Config.DBUsername, Config.DBPassword),
        new MySQLAdapter)
      )

		// Attempt to create the schema.  It will throw an error if it already exists
		try {
			transaction {
				Schema.create
				Logger.info("Schema created")
			}
		} catch {
			case _ =>		
		}
	}
}
