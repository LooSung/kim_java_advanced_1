package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class CallableMainV1 {
	public static void main(String[] args) throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(1);
		Future<Integer> future = es.submit(new MyCallable());
		Integer result = future.get();
		es.close();
		log("result value: " + result);
	}

	static class MyCallable implements Callable<Integer> {
		@Override
		public Integer call() {
			log("Callable 시작");
			sleep(3000);
			int value = new Random().nextInt(10);
			log("create value: " + value);
			log("Callable 완료");
			return value;
		}
	}
}
