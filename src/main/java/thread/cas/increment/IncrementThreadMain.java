package thread.cas.increment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.ThreadUtils.sleep;

public class IncrementThreadMain {
	public static final int THREAD_COUNT = 1000;

	public static void main(String[] args) {
		test(new BaseInteger());
		test(new VolatileInteger());
		test(new SynchronizedInteger());
		test(new MyAtomicInteger());
	}

	private static void test(IncrementInteger incrementInteger) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				sleep(10);
				incrementInteger.increment();
			}
		};

		List<Thread> threads  = new ArrayList<>();
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

		int result = incrementInteger.get();
		System.out.println(incrementInteger.getClass().getSimpleName() + "result : " + result );
		//log();
	}
}
