package com.joepritzel.rsce.event
import java.util.concurrent.TimeUnit

/**
 * An abstract class that all scheduled events must extend.
 *
 * @author Joe Pritzel
 */
abstract class ScheduledEvent[T](val delay: Long, val unit: TimeUnit, arg: T) extends Event[T] with Runnable {
  override def run {
    fire(arg)
  }
}