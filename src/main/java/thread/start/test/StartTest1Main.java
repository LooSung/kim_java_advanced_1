package thread.start.test;

import static util.LoggerUtils.log;

public class StartTest1Main {

	public static void main(String[] args) {
		CounterThread thread = new CounterThread();
		thread.start();
	}

	static class CounterThread extends Thread {

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
