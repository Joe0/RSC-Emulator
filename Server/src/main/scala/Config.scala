package com.joepritzel.rsce

/**
 * A class that contains all the settings for the server.
 *
 * @author Joe Pritzel
 */
object Config {
  /**
   * Enable this if you want to only accept connections with a specific version number.
   */
  val versionProtection = true

  /**
   * The version number of the client.
   */
  val version = 39

  /**
   * The max amount of players online.  Set to 0 for no max.
   */
  val maxPlayers = 0

	val DBUsername = "rsce"

	val DBPassword = ""

	val DBName = "rsce"

	val DBHost = "jdbc:mysql://localhost:3306/"
}
