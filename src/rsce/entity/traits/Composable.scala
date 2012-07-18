package rsce.entity.traits

import java.util.concurrent.ConcurrentHashMap
import rsce.exception.DuplicateEntryException

trait Composable {
  private val attributes = new ConcurrentHashMap[Class[_ <: Any], Any]

  /**
   * @return an attribute of the given type.
   */
  def ->[T](c: Class[T]) = get(c)
  /**
   * @return an attribute of the given type.
   */
  def get[T](c: Class[T]) = attributes.get(c).asInstanceOf[T]

  /**
   * Adds an attribute to the entity.
   */
  def addAttribute[T](i: T) = {
    val c = i.getClass
    val prev = attributes.get(c)
    if (prev == null)
      attributes.put(c, i)
    else
      throw new DuplicateEntryException("Entry already exists with value of " + prev)
  }

  /**
   * Adds an attribute to the entity.
   */
  def +=[T](i: T) = addAttribute(i)
}