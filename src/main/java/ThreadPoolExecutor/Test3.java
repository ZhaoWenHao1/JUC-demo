package ThreadPoolExecutor;

import org.omg.PortableServer.ThreadPolicy;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: JUC-demo
 * @description: Test3
 * @author: zwh
 * @create: 2021-04-21 16:25
 **/
public class Test3 {
    public static void main(String[] args) throws InterruptedException{
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4,5,5, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
//        pool.allowCoreThreadTimeOut(true);
        System.out.println(pool.allowsCoreThreadTimeOut());
        for(int i = 0;i < 4;i++){
            MyRunnable3 runnable3 = new MyRunnable3();
            pool.execute(runnable3);
        }
        Thread.sleep(1000);
        System.out.println(pool.getPoolSize());
//        pool.shutdown();MyCallableA
    }
}
