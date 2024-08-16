package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static util.ExecutorUtils.printState;
import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV3 {
	public static void main(String[] args) {
		// SynchronousQueue 사용 : BlockingQueue 인터페이스 구현체중 하나이다.
		// 내부 저장 공간이 없고, Producer Task의 작업을 Consumer 에게 직접 전달.
		// 쉽게 가운데에 버퍼를 두지 않고, 직접적으로 생산, 소비자에게 직접 전달한다.
		ExecutorService es = Executors.newCachedThreadPool();

		// keepAliveTime 60초 -> 3초로 조절
		// ThreadPoolExecutor es = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3, TimeUnit.SECONDS, new SynchronousQueue<>());

		log("Pool 생성");
		printState(es);

		for(int i = 0; i <= 4; i++) {
			String taskName = "task" + i;
			es.execute(new RunnableTask(taskName));
			printState(es, taskName);
		}

		sleep(3000);

		es.close();
		log("shutdown 완료");
	}
}
