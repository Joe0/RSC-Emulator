package rsce.entity.traits

import rsce.core.io.IOManager
import rsce.entity.{ World, Entity }
import rsce.task.{ SaveEntityTask, LoadEntityTask }
import javax.inject.Inject

trait Persistent extends Entity {

  @Inject private var io : IOManager = null

  // If called frequently, we might want to store the instance of IOManager
  def save = io.dispatch(SaveEntityTask(this))
  def load = io.dispatch(LoadEntityTask(this))
}