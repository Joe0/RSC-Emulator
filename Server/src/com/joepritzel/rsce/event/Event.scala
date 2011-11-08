package com.joepritzel.rsce.event
import scala.actors.DaemonActor
import com.joepritzel.rsce.net.Packet

/**
 * The trait that all Events must have.
 */
trait Event[T] {
  def fire(p: T);
}