package rsce.service

import rsce.entity.{ Entity => E }
import rsce.entity.traits.{ Networked => N }
import rsce.event._
import rsce.valueobject.{ Packet => P }

object PacketDecodingService {

  val map = createMapping

  def decode(entity : E with N, p : P) = {
    map.get(p.opcode) match {
      case Some(d) => Some(d(entity, p))
      case None    => None
    }
  }

  def createMapping = {
    import Functions._

    val m = new scala.collection.mutable.HashMap[Int, (E with N, P) => Any]

    m += 0 -> dummy _
    m += 3 -> trap _
    m += 5 -> ping _
    m += 7 -> report _
    m += 16 -> invUseOnPlayer _
    m += 17 -> castOnGameObject _
    m += 25 -> addIgnore _
    m += 27 -> invUseOnItem _
    m += 32 -> sessionRequest _
    m += 34 -> invUseOnGroundItem _
    m += 35 -> declineDuel _
    m += 36 -> invUseItemOnDoor _
    m += 39 -> logout _

    m.toMap // Converts to an immutable map
  }
}

private object Functions {

  /*
   *  Misc
   */
  def dummy(entity : E with N, p : P) = DummyPacketEvent(entity, p.payload.readByte, p.payload.readShort)
  def trap(entity : E with N, p : P) = TrapPacketEvent(entity)
  def ping(entity : E with N, p : P) = PingPacketEvent(entity, p.payload.readByte)
  def report(entity : E with N, p : P) = ReportPacketEvent(entity, p.payload.readLong, p.payload.readByte)
  def sessionRequest(entity : E with N, p : P) = SessionRequestPacketEvent(entity, p.payload.readByte, p.payload.getString().trim)
  def logout(entity : E with N, p : P) = LogoutPacketEvent(entity)

  /*
   * Inv
   */
  def invUseOnPlayer(entity : E with N, p : P) = InvUseOnPlayerPacketEvent(entity, p.payload.readLong, p.payload.readShort)
  def invUseOnItem(entity : E with N, p : P) = InvUseOnItemPacketEvent(entity, p.payload.readShort, p.payload.readShort)
  def invUseOnGroundItem(entity : E with N, p : P) = InvUseOnGroundItemPacketEvent(entity, p.payload.getPoint, p.payload.readShort, p.payload.readShort)
  def invUseItemOnDoor(entity : E with N, p : P) = InvUseOnDoorPacketEvent(entity, p.payload.getPoint, p.payload.readByte, p.payload.readShort)

  /*
   * Spells
   */
  def castOnGameObject(entity : E with N, p : P) = CastOnGameObjectPacketEvent(entity, p.payload.readShort, p.payload.getPoint)

  /*
   * Friend/Ignore
   */
  def addIgnore(entity : E with N, p : P) = AddIgnorePacketEvent(entity, p.payload.readLong)

  /*
   * Dueling
   */
  def declineDuel(entity : E with N, p : P) = DeclineDuelPacketEvent(entity)

}
