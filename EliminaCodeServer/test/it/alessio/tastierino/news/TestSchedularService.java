package it.alessio.tastierino.news;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestSchedularService {
	long sleep = 500;

	@Test
	public void testLoop2() throws Exception {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		ScheduledFuture future = executor.scheduleWithFixedDelay(new PollingService(), 0, sleep, TimeUnit.MILLISECONDS);
		Thread.sleep(2 * sleep);
		future.cancel(false);
		executor.shutdown();
	}
}

class PollingService implements Runnable {
	private int count = 0;

	public void run() {
		System.out.println("iteration :" + (count++));
	}
}