package rsce.event

import rsce.entity.{ Entity => E }
import rsce.entity.traits.{ Networked => N }
import rsce.valueobject.Point

case class DummyPacketEvent(entity : E with N, unknownByte : Byte, clientVersion : Short)
case class TrapPacketEvent(entity : E with N)
case class PingPacketEvent(entity : E with N, data : Byte)
case class ReportPacketEvent(entity : E with N, reportedUsernameHash : Long, reason : Byte)
case class InvUseOnPlayerPacketEvent(entity : E with N, otherPlayerHash : Long, slot : Short)
case class InvUseOnItemPacketEvent(entity : E with N, slot1h : Short, slot2 : Short)
case class InvUseOnGroundItemPacketEvent(entity : E with N, location : Point, groundItemId : Short, slot : Short)
case class InvUseOnDoorPacketEvent(entity : E with N, location : Point, direction : Byte, slot : Short)
case class CastOnGameObjectPacketEvent(entity : E with N, spellId : Int, point : Point)
case class AddIgnorePacketEvent(entity : E with N, ignoreUsernameHash : Long)
case class SessionRequestPacketEvent(entity : E with N, userByte : Byte, username : String)
case class DeclineDuelPacketEvent(entity : E with N)
case class LogoutPacketEvent(entity : E with N)
case class SecondaryObjectAction(entity : E with N, location:Point)