package info.javacoding.rscemulator.server.log

/**
 * This object logs things.
 *
 * @author Joe Pritzel
 */
object Logger {

  // TODO: Make it save to the database as well.

  def warning(o: Any) {
    println("[WARNING] " + o)
  }

  def info(o: Any) {
    println("[INFO] " + o)
  }

  def error(o: Any) {
    println("[ERROR] " + o)
  }
}