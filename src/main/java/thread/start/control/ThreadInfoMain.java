package thread.start.control;

import thread.start.HelloRunnable;

import static util.LoggerUtils.log;

public class ThreadInfoMain {
	public static void main(String[] args) {

		/*
		2024-08-05 12:59:36.616 [     main] mainThread = Thread[#1,main,5,main]
		2024-08-05 12:59:36.619 [     main] mainThread.threadId = 1
		2024-08-05 12:59:36.620 [     main] mainThread.threadName = main
		2024-08-05 12:59:36.626 [     main] mainThread.getPriority() = 5
		2024-08-05 12:59:36.626 [     main] mainThread.ThreadGroup = java.lang.ThreadGroup[name=main,maxpri=10]
		2024-08-05 12:59:36.626 [     main] mainThread.getState() = RUNNABLE
		*/

		Thread mainTread = Thread.currentThread();
		log("mainThread = " + mainTread);
		log("mainThread.threadId = " + mainTread.threadId());
		log("mainThread.threadName = " + mainTread.getName());
		log("mainThread.getPriority() = " + mainTread.getPriority());
		log("mainThread.ThreadGroup = " + mainTread.getThreadGroup());
		log("mainThread.getState() = " + mainTread.getState());


		/*
		2024-08-05 13:03:27.128 [     main] myTread = Thread[#21,myThread,5,main] // [id, name, priority, group] 기준 정보를 알려줌
		2024-08-05 13:03:27.128 [     main] myTread.threadId = 21 // 자바 Application 에서 자동적으로 생성
		2024-08-05 13:03:27.128 [     main] myTread.threadName = myThread // Thread 설정 이름 없으면, 자동생성
		2024-08-05 13:03:27.128 [     main] myTread.getPriority() = 5 // 기본 값은 5이며 1까지 할 수 있다. setPriority 로 우선 순위 설정 가능
		2024-08-05 13:03:27.129 [     main] myTread.ThreadGroup = java.lang.ThreadGroup[name=main,maxpri=10] // 기본적으로 Thread 는 부모 스레드와 같이 묶여서 나온다. 그래서 처음 Thread 인 main Thread 가 된다
		2024-08-05 13:03:27.129 [     main] myTread.getState() = NEW // NEW, RUNNABLE, BLOCKED, TIMED_WAITING, TERMINATED
		*/

		Thread myTread = new Thread(new HelloRunnable(), "myThread");
		log("myTread = " + myTread);
		log("myTread.threadId = " + myTread.threadId());
		log("myTread.threadName = " + myTread.getName());
		log("myTread.getPriority() = " + myTread.getPriority());
		log("myTread.ThreadGroup = " + myTread.getThreadGroup());
		log("myTread.getState() = " + myTread.getState());
	}
}
