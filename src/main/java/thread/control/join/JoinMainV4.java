package thread.control.join;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {
	public static void main(String[] args) throws InterruptedException {
		log("Start");

		SumJob sumJon1 = new SumJob(0, 50);
		SumJob sumJon2 = new SumJob(50, 100);

		Thread thread1 = new Thread(sumJon1, "Thread-1");
		Thread thread2 = new Thread(sumJon2, "Thread-2");

		thread1.start();
		thread2.start();

		log("join() - Main Thread 가 Thread-1, Thread-2가 종료 될때 까지 대기");
		thread1.join();
		thread2.join();
		log("Main Thread 대기 완료");

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
