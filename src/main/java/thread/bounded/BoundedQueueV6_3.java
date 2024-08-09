package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.LoggerUtils.log;

public class BoundedQueueV6_3 implements BoundedQueue {

	private BlockingQueue<String> queue;

	public BoundedQueueV6_3(int max) {
		this.queue = new ArrayBlockingQueue<String>(max);
	}

	@Override public void put(String data) {
		boolean result = false;
		// BoundedQueueV2와 같다.
		try {
			//  대기 시간 설정
			result = queue.offer(data, 1 , TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		log("try save result = " + result);
	}

	@Override public String take() {
		// BoundedQueueV2와 같다.
		try {
			//  대기 시간 설정
			return queue.poll(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override public String toString() {
		return queue.toString();
	}
}
