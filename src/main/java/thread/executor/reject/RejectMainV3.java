package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV3 {
	public static void main(String[] args) {
		// CallerRunsPolicy : ThreadPoolExecutor 가 아닌.. 명령을 내린 Main Thread가 직접 실행한다...
		ExecutorService es = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

		es.submit(new RunnableTask("Task-1"));
		es.submit(new RunnableTask("Task-2"));
		es.submit(new RunnableTask("Task-3"));
		es.submit(new RunnableTask("Task-4"));

		es.close();

	}
}
