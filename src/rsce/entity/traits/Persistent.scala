package rsce.entity.traits

import rsce.core.io.IOManager
import rsce.entity.{World, Entity}
import rsce.task.{SaveEntityTask, LoadEntityTask}

trait Persistent extends Entity {
  // If called frequently, we might want to store the instance of IOManager
  def save = World.injector.getInstance(classOf[IOManager]).dispatch(SaveEntityTask(this))
  def load = World.injector.getInstance(classOf[IOManager]).dispatch(LoadEntityTask(this))
}