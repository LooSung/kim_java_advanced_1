package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static util.ExecutorUtils.printState;
import static util.LoggerUtils.log;

public class PoolSizeMainV2 {
	public static void main(String[] args) {
		// BlockingQueue에 무한으로 넣게 되고, Thread수가 고정되어있다.
		ExecutorService es = Executors.newFixedThreadPool(2);
		log("Pool 생성");
		printState(es);

		for(int i = 0; i <= 6; i++) {
			String taskName = "task" + i;
			es.execute(new RunnableTask(taskName));
			printState(es, taskName);
		}

		es.close();
		log("shutdown 완료");
	}
}
