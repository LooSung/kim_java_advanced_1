package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV1 {
	public static void main(String[] args) {
		log("main() start");

		CustomMyRunnable runnable = new CustomMyRunnable();
		new Thread(runnable).start();

		log("main() end");
	}

	static class CustomMyRunnable implements Runnable {
		@Override
		public void run() {
			log("run()");
		}
	}
}
