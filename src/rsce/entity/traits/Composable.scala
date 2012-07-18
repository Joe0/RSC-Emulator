package rsce.entity.traits

import java.util.concurrent.ConcurrentHashMap

import rsce.exception.DuplicateEntryException

trait Composable {
  private val attributes = new ConcurrentHashMap[Class[_ <: Any], Any]

  /**
    * @return an attribute of the given type.
    */
  def ->[T](c : Class[T]) = get(c)
  /**
    * @return an attribute of the given type.
    */
  def get[T](c : Class[T]) = attributes.get(c).asInstanceOf[T]

  /**
    * Adds an attribute to the entity.
    */
  @throws(classOf[DuplicateEntryException])
  def addAttribute[T](i : T) = {
    val prev = attributes.putIfAbsent(i.getClass, i)
    if (prev != null)
      throw new DuplicateEntryException("Entry already exists with value of " + prev)
  }

  /**
    * Adds an attribute to the entity.
    */
  @throws(classOf[DuplicateEntryException])
  def +=[T](i : T) = addAttribute(i)
}