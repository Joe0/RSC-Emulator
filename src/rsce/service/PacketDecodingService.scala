package rsce.service

import rsce.entity.Entity
import rsce.entity.traits.Networked
import rsce.event._
import rsce.valueobject.Packet

object PacketDecodingService {

  val map = createMapping

  def decode(entity : Entity with Networked, p : Packet) = {
    map.get(p.opcode) match {
      case Some(d) => Some(d(entity, p))
      case None    => None
    }
  }

  def createMapping = {
    import Functions._

    val m = new scala.collection.mutable.HashMap[Int, (Entity with Networked, Packet) => Any]

    m += 0 -> dummy _
    m += 3 -> trap _
    m += 5 -> ping _
    m += 7 -> report _
    m += 16 -> invUseOnPlayer _
    m += 17 -> castOnGameObject _
    m += 25 -> addIgnore _
    m += 27 -> invUseOnItem _
    m += 32 -> requestSession _
    m += 34 -> invUseOnGroundItem _
    m += 35 -> declineDuel _
    m += 36 -> invUseItemOnDoor _
    m += 39 -> logout _
    m += 40 -> secondaryObjectAction _
    m += 42 -> changeStyle _
    m += 48 -> closeBank _

    m.toMap // Converts to an immutable map
  }
}

private object Functions {

  type E = Entity with Networked
  type P = Packet

  /*
   *  Misc
   */
  def dummy(entity : E, p : P) = DummyEvent(entity, p.payload.readByte, p.payload.readShort)
  def trap(entity : E, p : P) = TrapEvent(entity)
  def ping(entity : E, p : P) = PingEvent(entity, p.payload.readByte)
  def report(entity : E, p : P) = ReportEvent(entity, p.payload.readLong, p.payload.readByte)
  def requestSession(entity : E, p : P) = RequestSessionEvent(entity, p.payload.readByte, p.payload.readString().trim)
  def logout(entity : E, p : P) = LogoutEvent(entity)
  def changeStyle(entity : E, p : P) = ChangeStyleEvent(entity, p.payload.readByte)

  /*
   * Inv
   */
  def invUseOnPlayer(entity : E, p : P) = InvUseOnPlayerEvent(entity, p.payload.readLong, p.payload.readShort)
  def invUseOnItem(entity : E, p : P) = InvUseOnItemEvent(entity, p.payload.readShort, p.payload.readShort)
  def invUseOnGroundItem(entity : E, p : P) = InvUseOnGroundItemEvent(entity, p.payload.readPoint, p.payload.readShort, p.payload.readShort)
  def invUseItemOnDoor(entity : E, p : P) = InvUseOnDoorEvent(entity, p.payload.readPoint, p.payload.readByte, p.payload.readShort)

  /*
   * Spells
   */
  def castOnGameObject(entity : E, p : P) = CastOnGameObjectEvent(entity, p.payload.readShort, p.payload.readPoint)

  /*
   * Friend/Ignore
   */
  def addIgnore(entity : E, p : P) = AddIgnoreEvent(entity, p.payload.readLong)

  /*
   * Dueling
   */
  def declineDuel(entity : E, p : P) = DeclineDuelEvent(entity)

  /*
   * Object interaction
   */
  def secondaryObjectAction(entity : E, p : P) = SecondaryObjectActionEvent(entity, p.payload.readPoint)

  /*
   * Bank
   */
  def closeBank(entity : E, p : P) = CloseBankEvent(entity)

}
