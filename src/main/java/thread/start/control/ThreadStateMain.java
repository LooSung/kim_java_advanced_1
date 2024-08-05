package thread.start.control;

import static util.MyLogger.log;

public class ThreadStateMain {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new MyRunnable(), "MyThread");
		log("myThread.state1 = " + thread.getState()); // 1. NEW
		log("myThread.start");
		thread.start();

		Thread.sleep(1000);
		log("myThread.state3 = " + thread.getState()); // 3. TIMED_WAITING

		Thread.sleep(4000);
		log("myThread.state5 = " + thread.getState()); // 5. TERMINATED
	}

	static class MyRunnable implements Runnable {
		@Override
		public void run() {
			try {
				log("Start");
				log("myThread.state2 = " + Thread.currentThread().getState()); // 2. RUNNABLE

				log("sleep() start");
				Thread.sleep(3000);

				log("sleep() end");
				log("myThread.state4 = " + Thread.currentThread().getState()); // 4. RUNNABLE
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
