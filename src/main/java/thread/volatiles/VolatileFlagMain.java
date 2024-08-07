package thread.volatiles;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");

		log("Flag : " + task.flag);
		thread.start();

		sleep(1000);
		log("Flag : false 로 변경");
		task.flag = false;

		log("Flag : " + task.flag);
		log("Main Thread 종료");
	}

	static class MyTask implements Runnable {
		boolean flag = true;

		@Override
		public void run() {
			log("task 시작");

			while(flag) {

			}

			log("task 종료");
		}
	}
}
