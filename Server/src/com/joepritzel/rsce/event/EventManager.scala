package com.joepritzel.rsce.event

import com.joepritzel.rsce.event.impl._
import com.joepritzel.rsce.net.Packet
import scala.actors.DaemonActor
import java.util.concurrent.Executors
import com.joepritzel.rsce.Config

/**
 * This object manages events of all kinds.
 *
 * @author Joe Pritzel
 */
object EventManager {

  val scheduler = Executors.newScheduledThreadPool(Config.eventManagerThreads)

  def process(p: Packet) {
    EventLookupService.lookup(p.opcode) match {
      case Some(event) => event.fire(p)
      case None => Logger.error("Dropped packet: " + p.opcode)
    }
  }

  /**
   * Fires the event after the delay has elapsed.
   */
  def delayedFire[T](e: ScheduledEvent[T]) {
    scheduler.schedule(e, e.delay, e.unit)
  }

  /**
   * Fires the event after the delay has elapsed at a fixed interval.
   */
  def fireAtFixedRate[T](e: RepeatingEvent[T]) {
    scheduler.scheduleAtFixedRate(e, e.delay, e.period, e.unit)
  }
}
