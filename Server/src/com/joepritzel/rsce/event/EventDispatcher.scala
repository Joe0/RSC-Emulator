package com.joepritzel.rsce.event

import com.joepritzel.rsce.event.impl._
import com.joepritzel.rsce.net.Packet
import scala.actors.DaemonActor

/**
 * This object dispatches packets to the correct event object.
 *
 * @author Joe Pritzel
 */
object EventDispatcher {

  def process(p: Packet) {
    EventLookupService.lookup(p.opcode) match {
      case Some(event) => event.fire(p)
      case None => Logger.error("Dropped packet: " + p.opcode)
    }
  }
}
