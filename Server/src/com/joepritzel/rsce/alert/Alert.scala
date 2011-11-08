package com.joepritzel.rsce.alert

/**
 * Used to broadcast alerts to staff members.
 *
 * @author Joe Pritzel
 */
object Alert {
  def trapAlert(msg: String) {
    Logger.info(msg)
  }
}