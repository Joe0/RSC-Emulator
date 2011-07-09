package scala
import java.util.concurrent.atomic.AtomicReference

/**
 * Very simple property replacement that is meant to simulate language level properties.
 *
 * @threadsafety This class and its methods are thread safe
 *
 * @author Joe Pritzel
 */
class Property[T](var _value: T) {
  val value = new AtomicReference[T](_value)
  def :=(v: T) = value.set(v)
  def apply() = value.get
}