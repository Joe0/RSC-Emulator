package com.joepritzel.rsce.event

import com.joepritzel.rsce.event.impl._
import com.joepritzel.rsce.net.Packet
import scala.actors.DaemonActor

/**
 * This object dispatches packets to the correct event object.
 *
 * @author Joe Pritzel
 */
object EventDispatcher extends DaemonActor {

  private case class Stop()

  /**
   * Map of all events and their opcodes.
   */
  val mapping = Map[Int, Event](32 -> SessionOpen, 33 -> SessionOpen, 77 -> Login, 78 -> Login)

  mapping foreach { e => e._2.start }

  override def act {
    loop {
      react {
        case p: Packet => {
          Logger.info(p.opcode)
          mapping.get(p.opcode) match {
            case Some(actor) => actor !? p
            case None =>
          }
        }
      }
    }
  }

  def stop {
    while(this.mailboxSize > 0)
      Thread.sleep(25)
    mapping foreach { e => e._2.stop }
  }
}
