package com.joepritzel.rsce.log

/**
 * This object logs things.
 *
 * @author Joe Pritzel
 */
object Logger {

  // TODO: Make it save to the database as well.

  def warning(o: Any) {
    println("[warning] " + o)
  }

  def info(o: Any) {
    println("[info] " + o)
  }

  def error(o: Any) {
    println("[error] " + o)
  }
}