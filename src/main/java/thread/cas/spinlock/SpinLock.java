package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class SpinLock {
	private final AtomicBoolean lock = new AtomicBoolean(false);

	public void lock() {
		log("Try Get Lock");

		while (!lock.compareAndSet(false, true)) {
			log("Fail Get Lock - Spin Wait");
		}

		log("Success Get Lock");
	}

	public void unlock() {
		lock.set(false);
		log("Success Return Unlock");
	}
}
