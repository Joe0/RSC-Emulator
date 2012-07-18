package rsce.event

import rsce.entity.Entity
import rsce.entity.traits.Networked
import rsce.valueobject.Point

private[event] object TypeDefs {
  type E = Entity with Networked
}

import TypeDefs._

case class DummyPacketEvent(entity : E, unknownByte : Byte, clientVersion : Short)
case class TrapPacketEvent(entity : E)
case class PingPacketEvent(entity : E, data : Byte)
case class ReportPacketEvent(entity : E, reportedUsernameHash : Long, reason : Byte)
case class InvUseOnPlayerPacketEvent(entity : E, otherPlayerHash : Long, slot : Short)
case class InvUseOnItemPacketEvent(entity : E, slot1h : Short, slot2 : Short)
case class InvUseOnGroundItemPacketEvent(entity : E, location : Point, groundItemId : Short, slot : Short)
case class InvUseOnDoorPacketEvent(entity : E, location : Point, direction : Byte, slot : Short)
case class CastOnGameObjectPacketEvent(entity : E, spellId : Int, point : Point)
case class AddIgnorePacketEvent(entity : E, ignoreUsernameHash : Long)
case class SessionRequestPacketEvent(entity : E, userByte : Byte, username : String)
case class DeclineDuelPacketEvent(entity : E)
case class LogoutPacketEvent(entity : E)
case class SecondaryObjectAction(entity : E, location : Point)
case class StyleChangePacketEvent(entity : E, style : Byte)