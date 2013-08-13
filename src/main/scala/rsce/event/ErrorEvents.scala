package rsce.event

import rsce.entity.Entity
import rsce.entity.traits.Networked
import rsce.valueobject.Packet

case class EventDecodingFailed(entity : Entity with Networked, p : Packet)