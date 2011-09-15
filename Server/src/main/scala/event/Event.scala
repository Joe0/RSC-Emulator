package com.joepritzel.rsce.event
import scala.actors.DaemonActor

/**
 * The trait that all Events must have.
 */
trait Event extends DaemonActor {
  /**
   * Stops the actor once the mailbox is empty.
   */
  def stop {
    while (this.mailboxSize > 0) {
      Thread.sleep(25)
    }
  }
}