package thread.executor.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class CallableMainV2 {
	public static void main(String[] args) throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(1);
		log("submit() 호출");

		Future<Integer> future = es.submit(new MyCallable());
		log("submit() 즉시 반환, future = " + future);

		log("result.get() [Blocking] 메서드 호출 시작 Main Thread WAITING");
		// future.get()는 Thread.join() 과 같은 블로킹 메서드이다.
		Integer result = future.get(); // 결과가 나올때가지 기다린다...
		log("result.get() [Blocking] 메서드 호출 완료 Main Thread RUNNABLE");

		log("result value: " + result);
		log("future 완료!, future = " + future);
		es.close();
	}

	static class MyCallable implements Callable<Integer> {
		@Override
		public Integer call() {
			log("Callable 시작");
			sleep(2000);
			int value = new Random().nextInt(10);
			log("create value: " + value);
			log("Callable 완료");
			return value;
		}
	}
}
