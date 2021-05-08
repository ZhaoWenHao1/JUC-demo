package Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JUC-demo
 * @description: 信号量demo  用信号量控制同一时间只能有三个线程工作，或者说信号量（资源）为3
 * @author: zwh
 * @create: 2021-04-07 16:59
 **/
public class SemService {
    private Semaphore semaphore;
    
    private ReentrantLock lock = new ReentrantLock();
    
    public SemService(int n){
        semaphore = new Semaphore(n);
    }
    
    public void sayHello(){
        try {
            semaphore.acquire();
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " ready");
            System.out.println("begin hello " + System.currentTimeMillis());
            for(int i = 0;i < 20;i++){
                System.out.println(Thread.currentThread().getName() + " print " + (i + 1));
            }
            System.out.println("end hello " + System.currentTimeMillis());
            lock.unlock();
            semaphore.release();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " end");
        }catch (InterruptedException e){ 
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemService service = new SemService(3);

        MyThread[] threads = new MyThread[12];
        for(int i = 0;i < threads.length;i++){
            threads[i] = new MyThread(service);
            threads[i].start();
        }
    }
}

class MyThread extends Thread {
    private SemService service;

    public MyThread(SemService service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.sayHello();
    }
}
