package rsce.core.io;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

/**
 * Handles all io tasks, that is, any task that requires blocking.
 * 
 * @author Joe Pritzel
 * 
 */
@Singleton
public class IOManager {

	/**
	 * Handles io task listener subscriptions and io task dispatching.
	 */
	private final EventBus bus;

	private final ScheduledExecutorService scheduler;

	@Inject
	public IOManager(@Named("IOManager bus") EventBus bus,
			@Named("IOManager scheduler") ScheduledExecutorService scheduler) {
		this.bus = bus;
		this.scheduler = scheduler;
	}

	/**
	 * Registers an io task listener.
	 * 
	 * @param listener
	 *            - The io task listener to register.
	 */
	public void registerListener(Object listener) {
		bus.register(listener);
	}

	/**
	 * Unregisters an io task listener.
	 * 
	 * @param listener
	 *            - The io task listener to unregister.
	 */
	public void unregisterListener(Object listener) {
		bus.unregister(listener);
	}

	/**
	 * Dispatches an io task to all subscribed io task listeners.
	 * 
	 * @param task
	 *            - The io task to dispatch.
	 */
	public void dispatch(Object task) {
		bus.post(task);
	}

	/**
	 * Dispatches an io task to all subscribed io task listeners after the specified
	 * number of milliseconds.
	 * 
	 * @param task
	 *            - The io task to dispatch.
	 * @param delay
	 *            - The number of milliseconds to delay the dispatching of the
	 *            io task.
	 */
	public void dispatch(final Object task, long delay) {
		scheduler.schedule(new Runnable() {

			@Override
			public void run() {
				bus.post(task);
			}

		}, delay, TimeUnit.MILLISECONDS);
	}
}
