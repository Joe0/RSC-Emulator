package rsce.net;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

/**
 * This is the {@link ChannelPipelineFactory} that is used for connections to
 * the game server.
 * 
 * @author Joe Pritzel
 * 
 */
public class GameServerChannelPipelineFactory implements ChannelPipelineFactory {

	private final PacketEncoder encoder = new PacketEncoder();
	private final PacketDecoder decoder = new PacketDecoder();
	private final PacketHandler handler = new PacketHandler();

	// 4 Threads, no memory limit
	private final ExecutionHandler executionHandler = new ExecutionHandler(
			new OrderedMemoryAwareThreadPoolExecutor(4, 0, 0));

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		return Channels.pipeline(encoder, decoder, executionHandler, handler);
	}

}
