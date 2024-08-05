package thread.control.join;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class JoinMainV2 {
	// Thread 1, 2가 시작 되고 마지막에 Main 이 종료 되게 할려면 어떻게 될까?
	public static void main(String[] args) {
		log("Start");

		SumJob sumJon1 = new SumJob(1, 50);
		SumJob sumJon2 = new SumJob(50, 100);

		Thread thread1 = new Thread(sumJon1 , "Thread-1");
		Thread thread2 = new Thread(sumJon2 , "Thread-2");

		// Main Thread 는 밑의 두개의 Thread 를 기다려 주지 않는다.
		thread1.start();
		thread2.start();

		log("thread1.result = " + sumJon1.result);
		log("thread2.result = " + sumJon2.result);

		int sumAll = sumJon1.result + sumJon2.result;

		log("Sum = " + sumAll);

		log("End");
	}

	static class SumJob implements Runnable {
		int startValue;
		int endValue;
		int result = 0;

		public SumJob(int startValue, int endValue) {
			this.startValue = startValue;
			this.endValue = endValue;
		}

		@Override
		public void run() {
			log("SumJob Start");
			sleep(2000);

			int sum = 0;
			for (int i = startValue; i <= endValue; i++) {
				sum += i;
			}
			result = sum;

			log("SumJob End");
		}
	}
}
