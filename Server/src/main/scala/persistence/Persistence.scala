package com.joepritzel.rsce.persistence

import com.joepritzel.rsce.Config
import org.squeryl.{ SessionFactory, Session }
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.MySQLAdapter

object Persistence {
	def init = {
		val databaseUsername = Config.DBUsername
		val databasePassword = Config.DBPassword
		val databaseConnection = Config.DBHost + Config.DBName
		Class.forName("com.mysql.jdbc.Driver")
		SessionFactory.concreteFactory = Some(() => Session.create(
        java.sql.DriverManager.getConnection(databaseConnection, databaseUsername, databasePassword),
        new MySQLAdapter)
      )
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
