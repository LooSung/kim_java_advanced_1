package thread.cas.increment;

import java.util.ArrayList;
import java.util.List;

import static util.ThreadUtils.sleep;

public class IncrementPerformanceMain {
	public static final long COUTN = 100_000_000;

	public static void main(String[] args) {
		test(new BaseInteger());
		test(new VolatileInteger());
		test(new SynchronizedInteger());
		test(new MyAtomicInteger());
	}

	private static void test(IncrementInteger incrementInteger) {
		long startMs = System.currentTimeMillis();

		for (long i = 0; i < COUTN; i++) {
			incrementInteger.increment();
		}

		long endMs = System.currentTimeMillis();
		System.out.println(incrementInteger.getClass().getSimpleName() + " : " + (endMs - startMs));
	}
}
