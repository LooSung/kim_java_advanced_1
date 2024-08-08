package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV2(int max) {
		this.max = max;
	}

	@Override public synchronized void put(String data) {
		while(queue.size() == max) {
			log("[put] : Queue is full, so wait buffer");
			sleep(1000);
		}
		queue.offer(data);
	}

	@Override public synchronized String take() {
		while(queue.isEmpty()) {
			log("[take] Queue is empty, so wait buffer");
		}
		return queue.poll();
	}

	@Override public String toString() {
		return queue.toString();
	}
}
