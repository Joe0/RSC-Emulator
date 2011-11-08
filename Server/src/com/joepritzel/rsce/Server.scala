package com.joepritzel.rsce
import java.lang.management.ManagementFactory
import com.joepritzel.rsce.util.Stats

/**
 * The core of the server.
 *
 * @author Joe Pritzel
 */
object Server {
  def main(args: Array[String]): Unit = {
    Bootstrap.init
    Logger.info("Ready")
    readLine
    System.exit(0)
  }
}
