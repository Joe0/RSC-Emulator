package rsce.net;

import javax.inject.Inject;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;

/**
 * This is the {@link ChannelPipelineFactory} that is used for connections to
 * the game server.
 * 
 * @author Joe Pritzel
 * 
 */
public class GameServerChannelPipelineFactory implements ChannelPipelineFactory {

	private final PacketEncoder encoder;
	private final PacketDecoder decoder;
	private final PacketHandler handler;
	private final ExecutionHandler executionHandler;
	
	@Inject
	public GameServerChannelPipelineFactory(PacketEncoder encoder,
			PacketDecoder decoder, PacketHandler handler, ExecutionHandler executionHandler) {
		this.encoder = encoder;
		this.decoder = decoder;
		this.handler = handler;
		this.executionHandler = executionHandler;
	}

	

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		return Channels.pipeline(encoder, decoder, executionHandler, handler);
	}

}
