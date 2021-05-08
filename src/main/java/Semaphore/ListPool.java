package Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JUC-demo
 * @description: 信号量对并发执行任务的线程数量进行限制
 * @author: zwh
 * @create: 2021-04-11 19:17
 **/
public class ListPool {
    
    private int poolMaxSize = 3;
    private int semaphorePermits = 5;
    private List<String> list = new ArrayList<String>();
    private Semaphore concurrencySemaphore = new Semaphore(semaphorePermits);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();  // 有条件地唤醒线程
    
    public ListPool(){
        super();
        for (int i = 0;i < poolMaxSize;i++){
            list.add("str" + i);
        }
    }
    
    public String get(){
        String getString = null;
        try{
            concurrencySemaphore.acquire();
            lock.lock();
            while(list.size() == 0){
                condition.await();
            }
            getString = list.remove(0);
            lock.unlock();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return getString;
    }
    
    public void put(String stringValue){
        lock.lock();
        list.add(stringValue);
        condition.signalAll();
        lock.unlock();
        concurrencySemaphore.release();
    }

    public static void main(String[] args) {
        ListPool listPool = new ListPool();
        MyThreadListPool[] threads = new MyThreadListPool[12];
        for(int i = 0;i < threads.length;i++){
            threads[i] = new MyThreadListPool(listPool);
        }
        for(int i = 0;i < threads.length;i++){
            threads[i].start();
        }
    }
}

class MyThreadListPool extends Thread{
    private ListPool listPool;
    
    public MyThreadListPool(ListPool listPool){
        super();
        this.listPool = listPool;
    }

    @Override
    public void run() {
        for(int i = 0;i < Integer.MAX_VALUE;i++){
            String getString = listPool.get();
            System.out.println(Thread.currentThread().getName() + " get： " + getString);
            listPool.put(getString);
        }
    }
}
