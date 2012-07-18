package rsce.event

import rsce.entity.Entity
import rsce.entity.traits.Networked
import rsce.valueobject.Point

private[event] object TypeDefs {
  type E = Entity with Networked
}

import TypeDefs._

case class DummyEvent(entity : E, unknownByte : Byte, clientVersion : Short)
case class TrapEvent(entity : E)
case class PingEvent(entity : E, data : Byte)
case class ReportEvent(entity : E, reportedUsernameHash : Long, reason : Byte)
case class InvUseOnPlayerEvent(entity : E, otherPlayerHash : Long, slot : Short)
case class InvUseOnItemEvent(entity : E, slot1h : Short, slot2 : Short)
case class InvUseOnGroundItemEvent(entity : E, location : Point, groundItemId : Short, slot : Short)
case class InvUseOnDoorEvent(entity : E, location : Point, direction : Byte, slot : Short)
case class CastOnGameObjectEvent(entity : E, spellId : Int, point : Point)
case class AddIgnoreEvent(entity : E, ignoreUsernameHash : Long)
case class SessionRequestEvent(entity : E, userByte : Byte, username : String)
case class DeclineDuelEvent(entity : E)
case class LogoutEvent(entity : E)
case class SecondaryObjectActionEvent(entity : E, location : Point)
case class StyleChangeEvent(entity : E, style : Byte)
case class CloseBankEvent(entity : E)