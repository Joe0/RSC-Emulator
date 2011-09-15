package com.joepritzel.rsce.persistence

import com.joepritzel.rsce.Config
import org.squeryl.{ SessionFactory, Session }
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.MySQLAdapter
import java.sql.DriverManager

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
      DriverManager.getConnection(hostAndDatabase, Config.DBUsername, Config.DBPassword),
      new MySQLAdapter))

    try {
      transaction {
        Schema.create
        Logger.info("Schema created")
      }
    } catch {
      case e: Exception => Logger.info("Failed to create schema")
    }
  }
}
