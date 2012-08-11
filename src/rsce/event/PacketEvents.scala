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
case class CastOnInvItemEvent(entity : E, spellId : Int, slot : Short)
case class CastOnPlayerEvent(entity : E, spellId : Int, otherUserIndex : Short)
case class AddIgnoreEvent(entity : E, ignoreUsernameHash : Long)
case class RemoveFriendEvent(entity : E, removedFriendHash : Long)
case class RequestSessionEvent(entity : E, userByte : Byte, username : String)
case class DeclineDuelEvent(entity : E)
case class LogoutEvent(entity : E)
case class SecondaryObjectActionEvent(entity : E, location : Point)
case class PrimaryObjectActionEvent(entity : E, location : Point)
case class ChangeStyleEvent(entity : E, style : Byte)
case class CloseBankEvent(entity : E)
case class ConfirmTradeAcceptedEvent(entity : E)
case class ActivatePrayerEvent(entity : E, prayerId : Byte)
case class AttackPlayer(entity : E, otherUserIndex : Short)