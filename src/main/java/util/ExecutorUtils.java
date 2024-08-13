package util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.LoggerUtils.log;

public abstract class ExecutorUtils {

	public static void printState(ExecutorService executorService) {
		if (executorService instanceof ThreadPoolExecutor threadPoolExecutor) {
			int poolSize = threadPoolExecutor.getPoolSize();
			int activeCount = threadPoolExecutor.getActiveCount();
			int queueSize = threadPoolExecutor.getQueue().size();
			long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
			log("[Pool = " + poolSize + // 스레드 풀에서 관리되는 스레드 숫자
					"Active Count = " + activeCount + // 작업을 수행하는 스레드 숫자
					"Queue Size = " + queueSize + // 큐에 대기중인 작업의 숫자
					"Completed Task Count = " + completedTaskCount + "]"); // 완료된 작업의 숫자
		} else {
			log(executorService.toString());
		}
	}
}
