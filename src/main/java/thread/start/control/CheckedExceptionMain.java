package thread.start.control;

import static util.ThreadUtils.sleep;

public class CheckedExceptionMain {
	public static void main(String[] args) throws Exception {
		throw new Exception();
	}

	/*
	* Thread 의 강제 종료가 올 수 있고 멀티스레딩 환경에서는 예외 처리를 강조함으로써 스레드의 안정성 및 일관성 유지!
	* 오버라딩 된 메서드가 예외를 처리 하지않으면 하위 메서드는 처리 할 수 없다. (자바의 Exception 규칙)*/

	static class TempCheckedRunnable implements Runnable {
		@Override
		public void run() /*throws Exception*/ {
			/*throw new Exception();*/
		}
	}

	// Util 로 만들어 진행.
	static class CheckedRunnable implements Runnable {
		@Override
		public void run() {
			sleep(1000);
		}
	}
}
