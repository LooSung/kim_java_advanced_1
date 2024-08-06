package thread.control.interrupt.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV2 {

	public static void main(String[] args) throws InterruptedException{
		Printer printer = new Printer();
		Thread printerThread = new Thread(printer, "printer");
		printerThread.start();

		Scanner sc = new Scanner(System.in);
		while (true) {
			log("프린터할 문서를 입력하세요, 종료는 (q): ");
			String input = sc.nextLine();

			if(input.equals("q")){
				printerThread.interrupt();
				break;
			}

			printer.add(input);
		}
	}

	static class Printer implements Runnable {
		// ConcurrentLinkedQueue : 동시성 Concurrent을 사용해주자.
		Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

		@Override
		public void run() {
			while(!Thread.interrupted()) {
				if(jobQueue.isEmpty()) {
					continue;
				}

				try {
					String job = jobQueue.poll();
					log("출력 시작 : " + job + ", 대기문서 : " + jobQueue);
					Thread.sleep(3000);
					log("출력 완료");
				} catch (InterruptedException e) {
					log("Interrupted");
					break;
				}

			}

			log("프린터 종료");
		}

		public void add(String input) {
			jobQueue.offer(input);
		}
	}
}
