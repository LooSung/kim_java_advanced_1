package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static util.ExecutorUtils.printState;
import static util.LoggerUtils.log;

public class RejectMainV1 {
	public static void main(String[] args) {
		// AbortPolicy : 새로운 작업을 제출할 때 `RejectedExecutionException` 을 발생시킨다. 기본 정책이다
		ExecutorService es = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

		es.submit(new RunnableTask("Task-1"));
		try {
			es.submit(new RunnableTask("task2"));
		} catch (RejectedExecutionException e) {
			log("요청 초과");
			// 포기, 다시 시도 등 다양한 고민을 하면 됨
			log("Reject : " + e);
		}
		es.close();

	}
}
