package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static util.ExecutorUtils.printState;
import static util.ThreadUtils.sleep;

public class PreStartPoolMain {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(1000);
		printState(es);
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) es;
		threadPoolExecutor.prestartAllCoreThreads();
		sleep(100);
		printState(es);
	}
}
