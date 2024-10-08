package thread.executor.future;

import java.util.concurrent.*;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class SumTaskMainV2 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SumTask task1 = new SumTask(1, 50);
		SumTask task2 = new SumTask(51, 100);

		ExecutorService es = Executors.newFixedThreadPool(2);

		Future<Integer> future1 = es.submit(task1); // task 1 실행
		Future<Integer> future2 = es.submit(task2); // task 2 실행

		Integer sum1 = future1.get(); // future1.get() 실행 2초대기
		Integer sum2 = future2.get(); // future2.get() 실행 2초 대기, 만약 완료 되었다면 즉시 반환
 		log("task1 = " + sum1);
		log("task2 = " + sum2);

		int sumAll = sum1 + sum2;
		log("task1 + task2 = " + sumAll); // 총 2초 걸림

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