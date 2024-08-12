package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.LoggerUtils.log;

public class CasMainV2 {
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger(0);
		System.out.println("Start Value : " + atomicInteger.get()); // 0

		int resultValue1 = incrementAndGet(atomicInteger);
		System.out.println("Result Value1 : " + resultValue1);

		int resultValue2 = incrementAndGet(atomicInteger);
		System.out.println("Result Value2 : " + resultValue2);
	}

	// atomicInteger CompareAnsSet 직접구현 (성공)
	private static int incrementAndGet(AtomicInteger atomicInteger) {
		int getValue;
		boolean result;

		do {
			getValue = atomicInteger.get();
			log("value : " + getValue);
			result = atomicInteger.compareAndSet(getValue, getValue + 1);
			log("result : " + result);
		} while (!result) ;

		return getValue + 1;

	}
}
