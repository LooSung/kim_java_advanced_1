package thread.executor.test;

import java.util.concurrent.ExecutionException;

import static util.ThreadUtils.sleep;

public class OrderServiceMain {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		String orderNo = "NMK23103";

		OldOrderService oldOrderService = new OldOrderService();
		oldOrderService.order(orderNo);

		System.out.println();
		sleep(1000);

		NewOrderService newOrderService = new NewOrderService();
		newOrderService.order(orderNo);

	}
}
