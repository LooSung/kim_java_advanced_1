package thread.control.join;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {
	public static void main(String[] args) {
		log("Start");

		SumJob sumJon1 = new SumJob(0, 50);
		SumJob sumJon2 = new SumJob(50, 100);

		Thread thread1 = new Thread(sumJon1, "Thread-1");
		Thread thread2 = new Thread(sumJon2, "Thread-2");

		thread1.start();
		thread2.start();

		log("Main Thread Sleep()");
		sleep(3000);
		log("Main Thread Re-Start");

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
