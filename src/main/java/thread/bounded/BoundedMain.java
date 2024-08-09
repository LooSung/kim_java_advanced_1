package thread.bounded;

import java.util.ArrayList;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class BoundedMain {
	public static void main(String[] args) {
		BoundedQueue queue = new BoundedQueueV6_1(2);

		// 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!
		producerFirst(queue);
		// consumerFirst(queue);
	}

	private static void producerFirst(BoundedQueue queue) {
		log(" == [생산자 선 실행] 시작 " + queue.getClass() + " ==");
		ArrayList<Thread> threads = new ArrayList<>();
		startProducer(queue, threads);
		printAllState(queue, threads);
		startConsumer(queue, threads);
		printAllState(queue, threads);
		log(" == [생산자 선 실행] 종료 " + queue.getClass() + " ==");
	}

	private static void startProducer(BoundedQueue queue, ArrayList<Thread> threads) {
		System.out.println();
		log("생산자 시작");

		for (int i = 1; i <= 3; i++) {
			Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
			threads.add(producer);
			producer.start();

			sleep(100); // Thread 가 실행 하는동안 다음 Thread 로그를 보기 위해
		}
	}

	private static void printAllState(BoundedQueue queue, ArrayList<Thread> threads) {
		System.out.println();
		log("현재 상태 출력 [큐 데이터] " + queue);
		for(Thread thread : threads) {
			log(thread.getName() + " : " + thread.getState());
		}
	}

	private static void startConsumer(BoundedQueue queue, ArrayList<Thread> threads) {
		System.out.println();
		log("소비자 시작");

		for (int i = 1; i <= 3; i++) {
			Thread consume = new Thread(new ConsumerTask(queue), "consumer" + i);
			threads.add(consume);
			consume.start();

			sleep(100); // Thread 가 실행 하는동안 다음 Thread 로그를 보기 위해
		}
	}

	private static void consumerFirst(BoundedQueue queue) {
		log(" == [소비자 선 실행] 시작 " + queue.getClass() + " ==");
		ArrayList<Thread> threads = new ArrayList<>();
		startConsumer(queue, threads);
		printAllState(queue, threads);
		startProducer(queue, threads);
		printAllState(queue, threads);
		log(" == [소비자 선 실행] 종료 " + queue.getClass() + " ==");
	}
}
