package thread.start.test;

import static util.LoggerUtils.log;

public class StartTest4Main {

	public static void main(String[] args) {
		new Thread(new TestRunnable("A", 10000), "Thread-A").start();
		new Thread(new TestRunnable("B", 5000), "Thread-B").start();
	}

	static class TestRunnable implements Runnable {

		private final String content;
		private final int sleepTime;

		public TestRunnable(String content, int sleepTime) {
			this.content = content;
			this.sleepTime = sleepTime;
		}

		@Override
		public void run() {
			while (true) {
				log(content);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}
}