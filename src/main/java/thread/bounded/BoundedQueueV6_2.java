package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.LoggerUtils.log;

public class BoundedQueueV6_2 implements BoundedQueue {

	private BlockingQueue<String> queue;

	public BoundedQueueV6_2(int max) {
		this.queue = new ArrayBlockingQueue<String>(max);
	}

	@Override public void put(String data) {
		// BoundedQueueV1과 같다.
		boolean result = queue.offer(data);
		log("try save result = " + result);
	}

	@Override public String take() {
		// BoundedQueueV1과 같다.
		return queue.poll();
	}

	@Override public String toString() {
		return queue.toString();
	}
}
