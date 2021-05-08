package ScheduledExecutorService;
import java.util.concurrent.Callable;

public class MyCallableA implements Callable<String> {

	@Override
	public String call() throws Exception {
		try {
			System.out.println("MyCallableA "
					+ Thread.currentThread().getName() + " begin "
					+ System.currentTimeMillis());
			Thread.sleep(3000);
			System.out.println("MyCallableAEND "
					+ Thread.currentThread().getName() + "   end "
					+ System.currentTimeMillis());
		} catch (Exception e) {
			System.out.println(e.getMessage() + " ：左边信息就是捕获的异常信息");
			throw e;
		}
		return "returnA";
	}

}
