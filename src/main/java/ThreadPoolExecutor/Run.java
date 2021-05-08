package ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Run {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            List list = new ArrayList<>();
            list.add(new MyCallableB());
            list.add(new MyCallableA());

//            String getString = (String) service.invokeAny(list);
			
//            System.out.println("zzzz=" + getString);
			List<Future<String>> futures = service.invokeAll(list, 1, TimeUnit.SECONDS);
			System.out.println("start");
			service.shutdownNow();
//			for (int i = 0; i < futures.size(); i++) {
//				System.out.println("future " + i + " res :" + futures.get(i).get());
//			}
			for (int i = futures.size() - 1; i >= 0; i--) {
				System.out.println("future " + i + " res :" + futures.get(i).get());
			}
//			List<Future> futureList = new ArrayList<>();
//			list.forEach(callable -> {
//				futureList.add(service.submit(callable));
//			});
//			for (int i = 0; i < futureList.size(); i++) {
//				System.out.println(futureList.get(i).get());
//			}
        } catch (InterruptedException e) {
            System.out.println("main Interrupted_Exception");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("main Execution_Exception");
            e.printStackTrace();
        }  finally {
        	
            service.shutdown();
        }
    }
}
