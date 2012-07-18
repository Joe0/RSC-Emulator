package rsce.entity.traits
import org.jboss.netty.channel.Channel

trait Networked {
  private var channel: Channel = null

  lazy val getChannelId = channel.getId

  def setChannel(c: Channel) = channel = c

  def send(msg: Any) = channel.write(msg)

  def close = channel.close
}