package thread.control.join;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class JoinMainV5 {
	public static void main(String[] args) throws InterruptedException {
		log("Start");

		SumJob sumJon1 = new SumJob(0, 50);

		Thread thread1 = new Thread(sumJon1, "Thread-1");

		thread1.start();

		log("join(1000) - Main Thread 가 Thread-1가 1초 까지만 대기 그 후에는 Terminated");
		thread1.join(1000); // TIMED_WAITING
		log("Main Thread 대기 완료");

		log("thread1.result = " + sumJon1.result);

		log("Sum = " + sumJon1.result);

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

		@Override public void run() {
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
