package info.javacoding.rscemulator.server.event

import info.javacoding.rscemulator.server.log.Logger
import info.javacoding.rscemulator.server.event.impl._
import info.javacoding.rscemulator.server.net.Packet
import scala.actors.Actor

/**
 * This object dispatches packets to the correct event object.
 *
 * @author Joe Pritzel
 */
object EventDispatcher extends Actor {

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
}