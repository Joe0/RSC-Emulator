package rsce.module;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Serves as the configuration for the entire io system.
 * 
 * @author Joe Pritzel
 * 
 */
public class IOModule extends AbstractModule {

	@Override
	protected void configure() {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
		bind(EventBus.class).annotatedWith(Names.named("IOManager bus"))
				.toInstance(new AsyncEventBus(pool));
		bind(ScheduledExecutorService.class).annotatedWith(
				Names.named("IOManager scheduler")).toInstance(pool);
	}

}
