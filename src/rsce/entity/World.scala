package rsce.entity

import com.google.inject.Guice
import rsce.module._
import java.util.concurrent.ConcurrentHashMap
import rsce.entity.traits.Networked

object World {
  val injector = Guice.createInjector(new EventModule, new IOModule, new NetworkingModule)
  val networkedEntities = new ConcurrentHashMap[Int, Entity with Networked]

  def getNetworkedEntity(id: Int) = networkedEntities.get(id)
  def addNetworkedEntity(entity: Entity with Networked) = networkedEntities.put(entity.getChannelId, entity)
  def removeNetworkedEntity(id: Int) = networkedEntities.remove(id)
}