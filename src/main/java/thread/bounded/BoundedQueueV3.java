package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV3(int max) {
		this.max = max;
	}

	@Override public synchronized void put(String data) {
		while(queue.size() == max) {
			log("[put] : Queue is full, so wait buffer");

			try {
				// 대기 스레드가 생겨 현재 스레드가 대기 상태로 들어가게 됩니다.
				wait(); // RUNNABLE -> WAITING, 락 반납
				log("[put] : producer wakeup");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		queue.offer(data);
		log("[put] : producer save data, notify()");
		notify();
	}

	@Override public synchronized String take() {
		while(queue.isEmpty()) {
			log("[take] Queue is empty, so wait buffer");
			try {
				// 대기 스레드가 생겨 현재 스레드가 대기 상태로 들어가게 됩니다.
				wait(); // RUNNABLE -> WAITING, 락 반납
				log("[take] : consumer wakeup");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		String data = queue.poll();
		log("[put] : consumer get data, notify()");
		notify(); // WAIT -> BLOCKED

		return data;
	}

	@Override public String toString() {
		return queue.toString();
	}
}
