package com.joepritzel.rsce.event
import java.util.concurrent.TimeUnit

/**
 * This class should be extended for event that are going to be repeated.
 *
 * @author Joe Pritzel
 */
abstract class RepeatingEvent[T](delay: Long, val period: Long, unit: TimeUnit, args: T) extends ScheduledEvent(delay, unit, args)