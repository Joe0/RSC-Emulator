package com.joepritzel.rsce.util
import java.util.concurrent.atomic.AtomicReference
import Numeric.Implicits._

/**
 * Very simple property replacement that is meant to simulate language level properties for numeric types.
 *
 * @threadsafety This class and its methods are thread safe
 *
 * @author Joe Pritzel
 */
class Number[T: Numeric](var _value: T) {
  val value = new AtomicReference[T](_value)
  def :=(v: T) = value.set(v)
  def apply() = value.get
  def +=(v: T) = value.synchronized { value.set(value.get + v) }
}