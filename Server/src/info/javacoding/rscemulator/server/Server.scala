package info.javacoding.rscemulator.server
import info.javacoding.rscemulator.server.net.Bootstrap

/**
 * The core of the server.
 *
 * @author Joe Pritzel
 */
object Server {
  def main(args: Array[String]): Unit = {
    Bootstrap.init
    println("Ready to start")
    while (true) {
      Thread.sleep(5000)
    }
  }
}
