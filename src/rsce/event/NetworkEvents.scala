package rsce.event

import org.jboss.netty.channel.Channel

case class ChannelConnectedEvent(channel : Channel)
case class ChannelClosedEvent(channel : Channel)