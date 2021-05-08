package ThreadPoolExecutor;

public class MyRunnable1 implements Runnable {
	public void run() {
		try {
			for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
				String newString = new String();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();

				if (Thread.currentThread().isInterrupted() == true) {
					System.out.println("task failed finish, Interrupted");
					throw new InterruptedException();
				}
			}
			System.out.println("task finished");
		} catch (InterruptedException e) {
			System.out.println("into catch Interrupted task");
			e.printStackTrace();
		}
	}
}
