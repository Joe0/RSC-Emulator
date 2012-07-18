package rsce.event

import rsce.valueobject.Packet
import rsce.entity.traits.Networked
import rsce.entity.Entity

case class EventDecodingFailed(entity: Entity with Networked, p: Packet)