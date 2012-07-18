package rsce.valueobject

/**
  * Represents a point in-game.
  *
  * @author Joe Pritzel
  *
  */
sealed class Point(val x : Int, val y : Int) {
  override def equals(o : Any) = o match {
    case p : Point => p.x == x && p.y == y
    case _         => false
  }
  override def toString = "(" + x + ", " + y + ")"
  override def hashCode = x << 16 | y
}