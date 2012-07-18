package rsce.task

import rsce.entity.Entity
import rsce.entity.traits.Persistent

case class LoadEntityTask(val entity : Entity with Persistent)