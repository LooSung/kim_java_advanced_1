package thread.start.test;

import static util.MyLogger.log;

public class StartTest2Main {

	public static void main(String[] args) {
		new Thread(new CounterRunnable(), "counter").start();
	}

	static class CounterRunnable implements Runnable {

		public void run() {
			for (int i = 1; i <= 5; i++) {
				log("value: " + i);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		}

	}
}
