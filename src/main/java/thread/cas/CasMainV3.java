package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class CasMainV3 {
	private static int THREAD_COUNT = 2;

	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger(0);
		System.out.println("Start Value : " + atomicInteger.get()); // 0

		Runnable runnable = new Runnable() {
			public void run() {
				incrementAndGet(atomicInteger);
			}
		};

		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < THREAD_COUNT; i++) {
			Thread thread = new Thread(runnable);
			threads.add(thread);
			thread.start();
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		int result = atomicInteger.get();
		System.out.println(atomicInteger.getClass().getSimpleName() + " result value : " +  result);

	}

	// atomicInteger CompareAnsSet 직접구현 (성공)
	private static int incrementAndGet(AtomicInteger atomicInteger) {
		int getValue;
		boolean result;

		do {
			getValue = atomicInteger.get();
			sleep(100); // Thread 동시 실행을 위해
			log("value : " + getValue);
			result = atomicInteger.compareAndSet(getValue, getValue + 1);
			log("result : " + result);
		} while (!result) ;

		return getValue + 1;

	}
}
