package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.LoggerUtils.log;

public class BoundedQueueV6_4 implements BoundedQueue {

	private BlockingQueue<String> queue;

	public BoundedQueueV6_4(int max) {
		this.queue = new ArrayBlockingQueue<String>(max);
	}

	@Override public void put(String data) {
		// IllegalStateException : Queue full
		queue.add(data);
	}

	@Override public String take() {
		// NoSuchElementException : Queue empty
		return queue.remove();
	}

	@Override public String toString() {
		return queue.toString();
	}
}
