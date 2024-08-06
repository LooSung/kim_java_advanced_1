package thread.control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {

	/*
	* 1000개의 스레드를 실행시킨다.
	* 각 스레드가 실행하는 로직은, 0 - 9 까지 출력
	* run()에 있는 1, 2, 3 을 변경하면서 실행
	*/
	static final int THREAD_COUNT = 1000;

	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			Thread thread = new Thread(new MyRunnable());
			thread.start();
		}
	}

	static class MyRunnable implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {

				// 1.empty - 아무것도 없이 그냥 실행 (하나의 스레드가 대부분 끝내고 진행)
				System.out.println(Thread.currentThread().getName() + " : " + i) ;

				// sleep(1); // sleep - 현재 스레드에 나노초를 줘서 TIMED_WAITING 상태로 전환한다 그리고 다음 스레드 실행하게 만들기

				Thread.yield(); // yield - 현재 스레드는 실행(RUNNABLE) 상태인데 포기하고 다른 스레드에게 실행 기회를 준다.
				// 스레드가 RUNNABLE 상태일 때 Running(실행상태) 그리고 Ready(실행대기)로 나눌 수 있다. 자바에서는 위의 두 상태를 구분 할 수 없고 RUNNABLE 로 표현한다.
			}
		}
	}
}
