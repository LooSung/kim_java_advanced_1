package thread.control.join;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class JoinMainV1 {
	// 밑의 상황이라면, Main Thread 가 Thread 1 , 2 보다 먼저 끝나서 종료된다.

	public static void main(String[] args) {
		log("Start");
		Thread thread1 = new Thread(new job(), "Thread-1");
		Thread thread2 = new Thread(new job(), "Thread-2");

		thread1.start();
		thread2.start();

		log("End");
	}

	static class job implements Runnable {
		@Override
		public void run() {
			log("Job Start");
			sleep(2000);
			log("Job End");
		}
	}
}
