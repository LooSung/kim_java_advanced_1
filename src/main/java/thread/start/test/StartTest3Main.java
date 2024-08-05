package thread.start.test;

import static util.MyLogger.log;

public class StartTest3Main {

	public static void main(String[] args) {
		Runnable runnable = () -> {
			for (int i = 1; i <= 5; i++) {
				log("value: " + i);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		};

		new Thread(runnable).start();
	}
}
