package rsce.valueobject

import java.nio.{ByteBuffer, Buffer}

class Packet(val opcode : Int, val length : Int, val payload : Payload)

class Payload(buf : ByteBuffer) {

  implicit def toPayload(buf : Buffer) : Payload = this

  def readByte = buf.get
  def readBytes(a : Array[Byte]) = buf.get(a)
  def readShort = buf.getShort
  def readChar = buf.getChar
  def readInt = buf.getInt
  def readLong = buf.getLong

  def readString(length : Int = -1) = {
    val remaining = buf.slice
    val data = new Array[Byte](if (length == -1) remaining.limit else length)
    remaining.get(data)
    new String(data)
  }

  def readPoint = new Point(readShort, readShort)

  def writeByte(b : Byte) : Payload = buf.put(b)
  def writeBytes(b : Array[Byte]) : Payload = buf.put(b)
  def writeShort(s : Short) : Payload = buf.putShort(s)
  def writeChar(c : Char) : Payload = buf.putChar(c)
  def writeInt(i : Int) : Payload = buf.putInt(i)
  def writeLong(l : Long) : Payload = buf.putLong(l)

  def writeString(s : String) : Payload = { s.getBytes.foreach(b => writeByte(b)); this }
  def writePoint(p : Point) : Payload = writeShort(p.x.toShort)

  def flip : Payload = buf.flip

  def toByteBuffer = buf
}