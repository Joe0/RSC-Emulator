package com.joepritzel.rsce.model
import org.jboss.netty.channel.group.DefaultChannelGroup
import com.joepritzel.rsce.net.Packet
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.Executors
import com.joepritzel.rsce.Config
import java.util.concurrent.TimeUnit
import scala.collection.mutable.ListBuffer

/**
 * This is to group players by region, and is used for things such as updating, and broadcasting packets to certain areas.
 *
 * @author Joe Pritzel
 */
class Region(val x: Int, val y: Int) {
  /**
   * The channel group for this region.
   */
  private val group = new DefaultChannelGroup(x + "," + y)

  /**
   * The queue of packets that should be sent during the next 'tick'.
   */
  private val packetQueue = new LinkedBlockingQueue[Packet]

  /**
   * The executor responsible for scheduling the 'tick' for each region.
   */
  Executors.newSingleThreadScheduledExecutor.schedule(new Runnable {
    import scala.collection.JavaConversions._

    override def run {
      val packets = ListBuffer.empty
      packetQueue.drainTo(packets)
      packets.foreach(broadcastNow)
    }
  }, Config.regionTickInterval, TimeUnit.MILLISECONDS)

  /**
   * Writes the packet to the entire region.
   */
  def broadcastNow(p: Packet) = group.write(p)

  /**
   * Queues the packet to be written to the region.
   */
  def broadcast(p: Packet) = packetQueue.offer(p)

  /**
   * Adds a player to the region.
   */
  def add(p: Player) {
    group.add(p.channel())
  }

  /**
   * Removes a player from the region.
   */
  def remove(p: Player) {
    group.remove(p.channel())
  }

}