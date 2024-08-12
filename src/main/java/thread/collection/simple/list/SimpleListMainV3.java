package thread.collection.simple.list;

import static util.LoggerUtils.log;

public class SimpleListMainV3 {
	public static void main(String[] args) {
		test(new SyncList());
	}

	private static void test(SyncList list) {
		log(list.getClass().getSimpleName());

		Runnable addA = new Runnable() {
			@Override public void run() {
				list.add("A");
				log("Thread 1 : List.add(A)");
			}
		};

		Runnable addB = new Runnable() {
			@Override public void run() {
				list.add("B");
				log("Thread-2 : List.add(B)");
			}
		};

		Thread thread1 = new Thread(addA, "Thread-1");
		Thread thread2 = new Thread(addB, "Thread-2");

		thread1.start();
		thread2.start();

		try {
			thread1.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		try {
			thread2.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		log("List : " + list);

	}
}
