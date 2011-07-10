package com.joepritzel.rsce

/**
 * The core of the server.
 *
 * @author Joe Pritzel
 */
object Server {
  def main(args: Array[String]): Unit = {
    Bootstrap.init
    Logger.info("Ready to start")
    while (true) {
      Thread.sleep(5000)
    }
  }
}
