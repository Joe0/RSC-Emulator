package com.joepritzel.rsce.event
import impl._

/**
 * Used to look up events by their opcode.
 *
 * @author Joe Pritzel
 */
object EventLookupService {

  /**
   * Map of all events and their opcodes.
   */
  private var mapping = Map[Int, Event[Packet]](32 -> SessionOpen, 33 -> SessionOpen, 77 -> Login, 78 -> Login, 5 -> FlaggedProcesses)

  /**
   * Returns previous value, if there was one.
   */
  def register(opcode: Int, event: Event[Packet]) = {
    val prev = mapping.get(opcode)
    mapping += ((opcode, event))
    prev
  }

  /**
   * Returns the event mapped to the specified opcode.
   */
  def lookup(opcode: Int) = {
    mapping.get(opcode)
  }

}