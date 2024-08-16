package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static util.LoggerUtils.log;

public class RejectMainV2 {
	public static void main(String[] args) {
		// DiscardPolicy : 조용히.. Task를 버린다..
		ExecutorService es = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

		es.submit(new RunnableTask("Task-1"));
		es.submit(new RunnableTask("Task-2"));
		es.submit(new RunnableTask("Task-3"));

		es.close();

	}
}
