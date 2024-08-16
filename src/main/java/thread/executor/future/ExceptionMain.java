package thread.executor.future;

import java.util.concurrent.*;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class ExceptionMain {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(1);
		log("작업 전달");

		Future<Integer> future = es.submit(new MyCallable());
		sleep(1000); // 디버깅하기 위해서

		try {
			log("future.get() 호출 시도, future.state() = " + future.state());
			Integer result = future.get();
			log("result : " + result);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			log("e = " + e);
			log("e = " + e.getCause()); // 원본 예외
		}

		es.close();
	}

	static class MyCallable implements Callable<Integer> {
		@Override public Integer call() {
			log("Callable 실행, 예외 발생");
			throw new IllegalStateException("Ex!");
		}
	}
}
