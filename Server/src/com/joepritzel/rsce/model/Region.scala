package com.joepritzel.rsce.model
import org.jboss.netty.channel.group.DefaultChannelGroup
import com.joepritzel.rsce.net.Packet
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.Executors
import com.joepritzel.rsce.Config
import java.util.concurrent.TimeUnit
import scala.collection.mutable.ListBuffer

class Region(val x: Int, val y: Int) {
  private val group = new DefaultChannelGroup(x + "," + y)
  private val packetQueue = new LinkedBlockingQueue[Packet]
  Executors.newSingleThreadScheduledExecutor.schedule(new Runnable {
    import scala.collection.JavaConversions._

    override def run {
      val packets = ListBuffer.empty
      packetQueue.drainTo(packets)
      packets.foreach(broadcastNow)
    }
  }, Config.regionTickInterval, TimeUnit.MILLISECONDS)

  def broadcastNow(p: Packet) = println(p)//group.write(p)
  def broadcast(p: Packet) = packetQueue.offer(p)

  def add(p: Player) {
    group.add(p.channel())
  }

  def remove(p: Player) {
    group.remove(p.channel())
  }

}