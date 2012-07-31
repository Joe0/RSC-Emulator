package rsce.config.module;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import rsce.core.event.EventHandler;
import rsce.core.io.IOManager;
import rsce.entity.Player;
import rsce.entity.PlayerProvider;
import rsce.entity.World;

import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;

/**
 * This serves as the configuration for all general aspects of RSCE.
 * 
 * @author Joe Pritzel
 * 
 */
public class RSCEModule extends AbstractModule {

	@SuppressWarnings("unchecked")
	@Override
	protected void configure() {

		bind(World.class).toInstance(new World());

		bind(Player.class).toProvider(
				(Class<? extends Provider<Player>>) PlayerProvider.class);

		bind(EventHandler.class).toInstance(
				new EventHandler(new AsyncEventBus(Executors
						.newSingleThreadExecutor()), Executors
						.newSingleThreadScheduledExecutor()));

		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
		bind(IOManager.class).toInstance(
				new IOManager(new AsyncEventBus(pool), pool));
	}
}
