package ThreadPoolExecutor;

/**
 * @program: JUC-demo
 * @description: MyRunnable3
 * @author: zwh
 * @create: 2021-04-21 16:23
 **/
public class MyRunnable3 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " begin " + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());
    }
}
