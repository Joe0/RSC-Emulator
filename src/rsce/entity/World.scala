package rsce.entity

import java.util.concurrent.ConcurrentHashMap

import com.google.inject.Guice

import rsce.entity.traits.Networked

class World {
  val networkedEntities = new ConcurrentHashMap[Int, Entity with Networked]

  def getNetworkedEntity(id : Int) = networkedEntities.get(id)
  def addNetworkedEntity(entity : Entity with Networked) = networkedEntities.put(entity.getChannelId, entity)
  def removeNetworkedEntity(id : Int) = networkedEntities.remove(id)
}