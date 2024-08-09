package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.LoggerUtils.log;

public class BoundedQueueV5 implements BoundedQueue {

	private final Lock lock = new ReentrantLock();

	private final Condition prodCondition = lock.newCondition(); // 생산자 대기 집합.
	private final Condition consCondition = lock.newCondition(); // 소비자 대기 집합.

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV5(int max) {
		this.max = max;
	}

	@Override public void put(String data) {
		lock.lock();

		try {
			while(queue.size() == max) {
				log("[put] : Queue is full, so wait buffer");

				try {
					// wait(); 자바 Object 안에있는 대기 스레드에 넣는다.
					prodCondition.await();
					log("[put] : producer wakeup");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

			queue.offer(data);
			log("[put] : producer save data, producer signal()");
			consCondition.signal();
		} finally {
			lock.unlock();
		}
	}

	@Override public String take() {
		lock.lock();

		try {
			while(queue.isEmpty()) {
				log("[take] Queue is empty, so wait buffer");
				try {
					consCondition.await();
					log("[take] : consumer wakeup");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

			String data = queue.poll();
			log("[put] : consumer get data, consumer signal()");
			prodCondition.signal();

			return data;
		} finally {
			lock.unlock();
		}
	}

	@Override public String toString() {
		return queue.toString();
	}
}
