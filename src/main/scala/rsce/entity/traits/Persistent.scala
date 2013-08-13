package rsce.entity.traits

import rsce.entity.{ World, Entity }
import rsce.task.{ SaveEntityTask, LoadEntityTask }
import javax.inject.Inject
import com.joepritzel.feather.PSBroker
import javax.inject.Named

trait Persistent extends Entity {

  @Inject
  @Named("IOHandler")
  private var io: PSBroker = null

  // If called frequently, we might want to store the instance of IOHandler
  def save = io.publish(SaveEntityTask(this))
  def load = io.publish(LoadEntityTask(this))
}