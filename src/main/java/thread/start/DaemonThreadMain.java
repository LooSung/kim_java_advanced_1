package thread.start;

public class DaemonThreadMain {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()  + " : main() start");
		DaemonThread thread = new DaemonThread();
		thread.setDaemon(true); // Daemon Thread 여부
		thread.start();

		System.out.println(Thread.currentThread().getName()  + " : main() end");
	}

	static class DaemonThread extends Thread {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()  + " : run() start");

			try {
				Thread.sleep(10000);
			}catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}

			System.out.println(Thread.currentThread().getName()  + " : run() end");
		}
	}
}
