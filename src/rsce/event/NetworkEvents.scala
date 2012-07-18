package rsce.event

import rsce.entity.traits.Networked
import rsce.entity.Entity
import rsce.valueobject.Packet
import org.jboss.netty.channel.Channel

case class ChannelConnectedEvent(channel: Channel)
case class ChannelClosedEvent(channel: Channel)