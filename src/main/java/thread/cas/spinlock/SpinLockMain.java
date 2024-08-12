package thread.cas.spinlock;

import static util.LoggerUtils.log;

public class SpinLockMain {
	public static void main(String[] args) {
		SpinLock spinLock = new SpinLock();

		Runnable runnable = new Runnable() {
			public void run() {
				spinLock.lock();
				// Critical Section
				try {
					log("Start 비즈니스 로직");
				} finally {
					spinLock.unlock();
				}
			}
		};

		Thread thread1 = new Thread(runnable, "Thread-1");
		Thread thread2 = new Thread(runnable, "Thread-2");

		thread1.start();
		thread2.start();
	}
}
