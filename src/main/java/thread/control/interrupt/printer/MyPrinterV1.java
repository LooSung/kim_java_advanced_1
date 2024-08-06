package thread.control.interrupt.printer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.LoggerUtils.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {

	public static void main(String[] args) {
		Printer printer = new Printer();
		Thread printerThread = new Thread(printer, "printer");
		printerThread.start();

		Scanner sc = new Scanner(System.in);
		while (true) {
			log("프린터할 문서를 입력하세요, 종료는 (q): ");
			String input = sc.nextLine();

			if(input.equals("q")){
				printer.work = false;
				break;
			}

			printer.add(input);
		}
	}

	static class Printer implements Runnable {
		volatile boolean work = true;
		// ConcurrentLinkedQueue : 동시성 Concurrent을 사용해주자.
		Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

		@Override
		public void run() {
			while(work) {
				if(jobQueue.isEmpty()) {
					continue;
				}

				String job = jobQueue.poll();
				log("출력 시작 : " + job + ", 대기문서 : " + jobQueue);
				sleep(3000);
				log("출력 완료");
			}

			log("프린터 종료");
		}

		public void add(String input) {
			jobQueue.offer(input);
		}
	}
}
