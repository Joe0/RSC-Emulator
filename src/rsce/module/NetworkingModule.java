package rsce.module;

import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

import rsce.core.event.EventHandler;
import rsce.entity.World;
import rsce.net.*;

import com.google.inject.AbstractModule;

/**
 * This serves as the configuration for the entire networking portion of the
 * server.
 * 
 * @author Joe Pritzel
 * 
 */
public class NetworkingModule extends AbstractModule {

	private final World world;
	private final EventHandler eventHandler;

	@Inject
	public NetworkingModule(World world, EventHandler eventHandler) {
		this.world = world;
		this.eventHandler = eventHandler;
	}

	@Override
	protected void configure() {

		ChannelFactory channelFactory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);

		requestInjection(PacketHandler.class);

		bootstrap.setPipelineFactory(new GameServerChannelPipelineFactory(
				new PacketEncoder(), new PacketDecoder(), new PacketHandler(
						world, eventHandler), new ExecutionHandler(
						new OrderedMemoryAwareThreadPoolExecutor(4, 0, 0))));

		bind(ServerBootstrap.class).toInstance(bootstrap);
	}
}
