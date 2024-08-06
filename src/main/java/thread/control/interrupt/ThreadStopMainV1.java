package thread.control.interrupt;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

	public static void main(String[] args) {
		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");
		thread.start(); // RUNNABLE

		sleep(4000); // TIMED_WAITING
		log("작업 중단 flag");
		task.runFlag = false; // 대략 2초 정도 뒤에 작업 종료... 지시
		// TERMINATE
	}

	static class MyTask implements Runnable {

		// volatile : 캣가 아닌 Main Memory에서 CPU의 R/W 작업된다.
		volatile boolean runFlag = true;

		@Override
		public void run() {
			while (runFlag) {
				log("작업 중");
				sleep(3000);
			}
			log("자원 정리");
			log("자원 종료");
		}
	}
}
