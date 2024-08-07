package thread.volatiles;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;


/*
2024-08-07 09:49:27.220 [     work] flag : true, count : 700000000while 중
2024-08-07 09:49:27.332 [     work] flag : true, count : 800000000while 중
2024-08-07 09:49:27.406 [     main] flag : false, count : 865829237Main 종료  --> Main Thread 종료 시점
2024-08-07 09:49:27.444 [     work] flag : true, count : 900000000while 중
2024-08-07 09:49:27.445 [     work] flag : false, count : 900000000while 종료 --> MyTask Thread 종료 시점

-- Volatile 적용
2024-08-07 09:55:34.352 [     work] task 시작
2024-08-07 09:55:34.458 [     work] flag : true, count : 100000000while 중
...
2024-08-07 09:55:35.326 [     work] flag : true, count : 500000000while 중
2024-08-07 09:55:35.340 [     main] flag : false, count : 506145170Main 종료
2024-08-07 09:55:35.340 [     work] flag : false, count : 506145170while 종료
*/

public class VolatileCountMain {
	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");
		thread.start();

		sleep(1000);
		task.flag = false;
		log("flag : " + task.flag + ", count : " + task.count + "Main 종료");
	}

	static class MyTask implements Runnable {
		/// boolean flag = true;
		volatile boolean flag = true;
		int count;

		@Override
		public void run() {
			log("task 시작");

			while(flag) {
				count++;
				if(count % 100_000_000 == 0 ) {
					log("flag : " + flag + ", count : " + count + "while 중");
				}
			}

			log("flag : " + flag + ", count : " + count + "while 종료");
		}
	}
}
