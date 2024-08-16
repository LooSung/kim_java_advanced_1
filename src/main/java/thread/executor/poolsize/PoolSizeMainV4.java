package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static util.ExecutorUtils.printState;
import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV4 {
	// static final int TASK_SIZE = 1100; // Normal

	// static final int TASK_SIZE = 1200; // Emergency
	// MaxPoolSize가 증가하기 시작하면, 작업이 완료된 Pool에 있던 Thread가 다른 Thread 작업까지 대신하준다.

	static final int TASK_SIZE = 1202; // Negative
	// 요청을 거절하게 되면 작업을 처리 하지않은고 예외 발생시킨다.
	// 더 들어오는 요청을 다시 시도해달라고 요청 해야한다.

	public static void main(String[] args) {
		ExecutorService es = new ThreadPoolExecutor(100, 200,
				60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
		printState(es);

		long startMs = System.currentTimeMillis();
		for(int i = 1; i <= TASK_SIZE; i++) {
			String taskName = "task" + i;
			try {
				es.execute(new RunnableTask(taskName));
				printState(es, taskName);
			} catch (RejectedExecutionException e) {
				log(taskName + " -> rejected" + e);
			}
		}

		es.close();
		log("shutdown 완료");
		long endMs = System.currentTimeMillis();
		log("Result Time : " + (endMs - startMs));
	}
}
