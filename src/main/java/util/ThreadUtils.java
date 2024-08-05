package util;

import static util.LoggerUtils.log;

public abstract class ThreadUtils {

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			log("인터럽트 발생, " + ex.getMessage());
			throw new RuntimeException(ex);
		}
	}
}
