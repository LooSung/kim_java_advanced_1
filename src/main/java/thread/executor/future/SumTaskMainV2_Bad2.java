package thread.executor.future;

import java.util.concurrent.*;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class SumTaskMainV2_Bad2 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SumTask task1 = new SumTask(1, 50);
		SumTask task2 = new SumTask(51, 100);

		ExecutorService es = Executors.newFixedThreadPool(2);

		Integer sum1 = es.submit(task1).get();
		log("task1 = " + sum1);
		// task1 작업 끝나고 2초 기다림

		Integer sum2 = es.submit(task2).get();
		log("task2 = " + sum2);
		// task2 작업 끝나고 2초 기다림

		// 4초가 걸리면서 비효율이 된다.

		int sumAll = sum1 + sum2;
		log("task1 + task2 = " + sumAll);

		es.close();
		log("End");
	}

	static class SumTask implements Callable<Integer> {
		int startValue;
		int endValue;

		public SumTask(int startValue, int endValue) {
			this.startValue = startValue;
			this.endValue = endValue;
		}

		@Override
		public Integer call() {
			log("작업 시작");
			sleep(2000);

			int sum = 0;
			for (int i = startValue; i <= endValue; i++) {
				sum += i;
			}

			log("작업 완료 result=" + sum);
			return sum;
		}
	}
}