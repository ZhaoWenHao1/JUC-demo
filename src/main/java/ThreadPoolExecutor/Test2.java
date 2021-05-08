package ThreadPoolExecutor;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test2 {
	public static void main(String[] args) throws InterruptedException {
		MyRunnable1 myRunnable = new MyRunnable1();
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 99999, 9999L,
				TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		pool.execute(myRunnable);
		pool.execute(myRunnable);
		pool.execute(myRunnable);
		pool.execute(myRunnable);
		Thread.sleep(1000);
		pool.getQueue().forEach(System.out::println);
		List<Runnable> runnables = pool.shutdownNow();
		runnables.forEach(System.out::println);
		System.out.println(pool.isShutdown());
		System.out.println(pool.isTerminating());
		Thread.sleep(3000);
		System.out.println(pool.isTerminating());
		System.out.println(pool.isTerminated());
//		pool.execute(myRunnable);
		System.out.println("main end!");
	}
}
