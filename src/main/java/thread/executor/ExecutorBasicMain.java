package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.ExecutorUtils.printState;
import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {

	public static void main(String[] args) {
		ExecutorService es = new ThreadPoolExecutor(2, 2,0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
		log("== 초기 상태 ==");
		printState(es);

		es.execute(new RunnableTask("Task-1"));
		es.execute(new RunnableTask("Task-2"));
		es.execute(new RunnableTask("Task-3"));
		es.execute(new RunnableTask("Task-4"));
		log("== 작업 수행 중 상태 ==");
		printState(es);

		sleep(3000);
		log("== 작업 수행 완료 ==");
		printState(es);

		es.close();
		log("== Shot Down ==");
		printState(es);

	}
}
