package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger(0);
		System.out.println("Start Value : " + atomicInteger.get()); // 0

		// 값이 0 이면, 1로 연산한다.
		// 원자적 연산이라 함음 int i = 0; 과 같은 하나의 연산을 하는 것이다.
		// 생각해봐야하는게, compareAndSet 은 if 연산 이라는 것이다. 이건 원자적 연산이 아니라

		boolean result1 = atomicInteger.compareAndSet(0, 1); // true
		System.out.println("result1 : " + result1 + ", value =  " + atomicInteger.get()); // 1

		boolean result2 = atomicInteger.compareAndSet(0, 1); // false
		System.out.println("result1 : " + result2 + ", value =  " + atomicInteger.get()); // 1
	}
}
