package thread.control.interrupt;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

	public static void main(String[] args) {
		MyTask task = new MyTask();

		Thread thread = new Thread(task, "work");
		thread.start(); // RUNNABLE

		sleep(10); // TIMED_WAITING
		log("작업 중단 지시");
		thread.interrupt(); // 바로 작업 중단 지시가 들어간다.
		log("work thread interrupted : " + thread.isInterrupted());// TERMINATE
	}

	static class MyTask implements Runnable {
		@Override
		public void run() {
			while(!Thread.interrupted()) { // 인터럽트 상태 변경 O
				log("작업 중");
			}
			log("work thread interrupted : " + Thread.currentThread().isInterrupted()); // RUNNABLE

			// 만약 여기서 try - catch() : sleep(3000); 을 한다면, Interrupt 상태는 다시 true로 바뀌고 Exception이 난다.
			// 이렇게 되면 위에 코드는 자원 정리를 잘 안해서 문제가 생기게 된다.

			// 결과 : 이런 상황을 방지 하기 위해서는 Interrupt 상태를 다시 정상 false로 바꿔줘야한다...
			log("자원 정리");
			log("자원 종료");
		}
	}
}
