package com.joepritzel.rsce.event.impl

/**
 * This object is for reading packets that contain information regarding processes that have been flagged by the client.<br>
 * This, in turn, flags the player on the server side.
 *
 * @author Joe Pritzel
 */
object FlaggedProcesses extends Event[Packet] {
  override def fire(p: Packet) {
    if (p.length > 0) {
      p.payload.readByte match {
        case 1 => p.player.sessionFlag += 1
      }
    }
  }
}