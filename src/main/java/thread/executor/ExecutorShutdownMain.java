package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.ExecutorUtils.printState;
import static util.LoggerUtils.log;

public class ExecutorShutdownMain {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(2);
		es.execute(new RunnableTask("Task-A"));
		es.execute(new RunnableTask("Task-B"));
		es.execute(new RunnableTask("Task-C"));
		es.execute(new RunnableTask("Task-Long", 100_000));
		printState(es);

		log("== ShutDown Start ==");
		shutdownAndAwaitTermination(es);
		log("== ShutDown Completed ==");
		printState(es);
	}

	private static void shutdownAndAwaitTermination(ExecutorService es) {
		es.shutdown(); // non-blocking, 새로운 작업을 받지 않으며, 큐에 대기중인 것은 사리진다.

		try {
			if(!es.awaitTermination(10, TimeUnit.SECONDS)) {
				log("서비스 정상종료 실패 -> 강제 종료 시도");
				es.shutdownNow();

				if(!es.awaitTermination(10, TimeUnit.SECONDS)) {
					log("서비스가 종료 되지 않았습니다.");
				}
			}
		} catch (InterruptedException e) {
			// awaitTermination()으로 대기중인 스레드가 인터럽트가 될 수 있따.
			es.shutdownNow();
		}
	}
}
