package com.joepritzel.rsce.model
import java.util.concurrent.{ ConcurrentHashMap, CopyOnWriteArrayList }
import scala.ref.WeakReference

/**
 * This object is the world, and contains references to everything inside of it.
 *
 * @author Joe Pritzel
 */
object World extends Entity {
  private val players = new CopyOnWriteArrayList[Player]
  private val idMap = new ConcurrentHashMap[Int, WeakReference[Player]]

  /**
   * Adds a player to the world.
   */
  def addPlayer(p: Player) = {
    idMap.putIfAbsent(p.channel().getId, new WeakReference(p))
    players.addIfAbsent(p)
  }

  /**
   * Removes a player from the world, and calls the dispose method.
   */
  def removePlayer(p: Player) = {
    p.dispose
    players.remove(p)
  }

  /**
   * Returns a Player when given the id from the channel.
   */
  def getPlayerById(id: Int) = {
    idMap.get(id).get match {
      case Some(x) => x
      case None => null
    }
  }

  /**
   * Returns the amount of players in the server.
   */
  def playerCount = players.size

  override def dispose {
    players.asInstanceOf[List[Player]].foreach(p => p.dispose)
  }
}