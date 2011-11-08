package com.joepritzel.rsce.event.impl
import com.joepritzel.rsce.event.RepeatingEvent
import java.util.concurrent.TimeUnit
import com.joepritzel.rsce.model.Player
import com.joepritzel.rsce.model.World
import scala.collection.JavaConversions._
import com.joepritzel.rsce.util.Stats

/**
 * This object is for restoring player's current stats when it's not equal to their real stats.
 *
 * @author Joe Pritzel
 */
object StatRestore {

  // TODO: Account for users relogging to avoid stat restore

  /**
   * Returns the event.
   */
  val event = new RepeatingEvent[AnyRef](0, 1, TimeUnit.MINUTES, None) {
    override def fire(none: AnyRef) {
      World.getPlayers.par.foreach {
        p =>
          for (i <- (0 until 18); if(i != 3)) {
            p.stats().setStat(i, p.stats().getStat(i) + add(difference(p, i)))
          }
      }
    }
    // Helper methods.
    def add(stat: Int) = stat / -Math.abs(stat)
    def difference(p: Player, index: Int) = p.stats().getStat(index) - p.stats().getRealStat(index)
  }
}