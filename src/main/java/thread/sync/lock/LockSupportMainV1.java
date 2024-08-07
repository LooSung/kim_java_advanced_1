package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new ParkTest(), "Thread-1");
		thread1.start();

		sleep(100);
		log("Thread-1 state : " + thread1.getState());

		log("main thread -> unpark");
		LockSupport.unpark(thread1);//  state : RUNNABLE, interrupted state : false
		// thread1.interrupt(); // state : RUNNABLE, interrupted state : true
	}

	static class ParkTest implements Runnable {
		@Override
		public void run() {
			log("park start");
			LockSupport.park();

			log("park end, state : " + Thread.currentThread().getState());
			log("interrupted state : " + Thread.currentThread().isInterrupted());
		}
	}
}
