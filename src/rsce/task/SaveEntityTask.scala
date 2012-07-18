package rsce.task

import rsce.entity.traits.Persistent
import rsce.entity.Entity

case class SaveEntityTask(val entity : Entity with Persistent)