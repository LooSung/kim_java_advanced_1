package thread.cas.spinlock;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {
	private volatile boolean lock = false;

	public void lock() {
		log("Try Get Lock");

		while (true) {
			if (!lock) {
				sleep(100);
				lock = true;
				break;
			} else {
				log("Fail Get Lock - Spin Wait");
			}
		}

		log("Success Get Lock");
	}

	public void unlock() {
		lock = false;
		log("Success Return Unlock");
	}
}
