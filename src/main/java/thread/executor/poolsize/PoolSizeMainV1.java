package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static util.ExecutorUtils.printState;
import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
	public static void main(String[] args) {
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
		ThreadPoolExecutor es = new ThreadPoolExecutor(2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);
		printState(es);

		es.execute(new RunnableTask("Task-1"));
		printState(es, "Task-1");

		es.execute(new RunnableTask("Task-2"));
		printState(es, "Task-2");

		es.execute(new RunnableTask("Task-3"));
		printState(es, "Task-3");

		es.execute(new RunnableTask("Task-4"));
		printState(es, "Task-4");

		es.execute(new RunnableTask("Task-5"));
		printState(es, "Task-5");

		es.execute(new RunnableTask("Task-6"));
		printState(es, "Task-6");

		// RejectedExecutionException
		try {
			es.execute(new RunnableTask("Task-7"));
			printState(es, "Task-7");
		} catch (RejectedExecutionException e) {
			log("RejectedExecutionException : " + e);
		}

		sleep(3000);
		log("Task 수행 완료");
		printState(es);

		sleep(3000);
		log("maximum time 대기 시간 초과");

		es.close();
		log("Shutdown 완료");
		printState(es);
	}
}
