package rsce.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.channel.ChannelHandler.Sharable;

import rsce.valueobject.Packet;

@Sharable
public class GSPacketEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel chan, Object o)
			throws Exception {
		Packet p = (Packet) o;
		if (p.opcode() != -1) {
			// Short + Byte + payload's length
			ChannelBuffer out = ChannelBuffers.directBuffer(2 + 1 + p.length());
			out.writeShort(p.length());
			out.writeByte(p.opcode());
			out.writeBytes(p.payload().toByteBuffer());
			return out;
		} else {
			ChannelBuffer out = ChannelBuffers.directBuffer(p.length());
			out.writeBytes(p.payload().toByteBuffer());
			return out;
		}
	}

}
