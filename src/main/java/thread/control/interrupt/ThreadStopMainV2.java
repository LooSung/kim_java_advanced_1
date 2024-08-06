package thread.control.interrupt;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

	public static void main(String[] args) {
		MyTask task = new MyTask();

		Thread thread = new Thread(task, "work");
		thread.start(); // RUNNABLE

		sleep(4000); // TIMED_WAITING
		log("작업 중단 지시");
		thread.interrupt(); // 바로 작업 중단 지시가 들어간다.
		log("work thread interrupted : " + thread.isInterrupted());// TERMINATE
	}

	static class MyTask implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					log("작업 중");
					Thread.sleep(3000); // RUNNABLE
				}
			} catch (InterruptedException e) {
				log("work thread interrupted : " + Thread.currentThread().isInterrupted()); // RUNNABLE
				log("InterruptedException : " + e.getMessage()); // RUNNABLE
				log("state = " + Thread.currentThread().getState()); // RUNNABLE
			}
			// TERMINATED
			log("자원 정리");
			log("자원 종료");
		}
	}
}
