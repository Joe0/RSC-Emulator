package com.joepritzel.rsce
import java.lang.management.ManagementFactory

/**
 * The core of the server.
 *
 * @author Joe Pritzel
 */
object Server {
  def main(args: Array[String]): Unit = {
    Bootstrap.init
    Logger.info("Ready to start")
    readLine
    Bootstrap.stop
  }
}
