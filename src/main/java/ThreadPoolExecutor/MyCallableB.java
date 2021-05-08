package ThreadPoolExecutor;

import java.util.concurrent.Callable;

public class MyCallableB implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("MyCallableB " + Thread.currentThread().getName()
				+ " begin " + System.currentTimeMillis());
//		Thread.sleep(5000);
		for (int i = 0; i < 999993456; i++) {
			if(Thread.currentThread().isInterrupted()){
				System.out.println("CB throw InterruptedException");
				throw new InterruptedException();
			}
			String newString = new String();
			Math.random();
			Math.random();
			Math.random();
			Math.random();
			Math.random();
//			System.out.println("MyCallableB 在运行中=" + (i + 1));
		}
		System.out.println("MyCallableBEND " + Thread.currentThread().getName()
				+ "   end " + System.currentTimeMillis());
//		throw new NullPointerException();
		return "returnB";
	}

}
