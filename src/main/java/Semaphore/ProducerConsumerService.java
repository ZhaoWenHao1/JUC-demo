package Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JUC-demo
 * @description: 生产者消费者问题  用信号量控制生产者和消费者的线程数量
 * @author: zwh
 * @create: 2021-04-11 19:35
 **/
public class ProducerConsumerService {
    volatile private Semaphore setSemaphore = new Semaphore(10); // 生产者最多有10个同时工作
    volatile private Semaphore getSemaphore = new Semaphore(20); // 消费者最多有20个同时工作
    volatile private ReentrantLock lock = new ReentrantLock();
    volatile private Condition setCondition = lock.newCondition();
    volatile private Condition getCondition = lock.newCondition();
    // buffer大小为4
    volatile private Object[] buffer = new Object[4];

    private boolean isEmpty() {
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isFull() {
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] == null) {
                return false;
            }
        }
        return true;
    }

    public void set() {
        try {
            setSemaphore.acquire();
            lock.lock();
            while (isFull()) {
                setCondition.await();
            }
            for (int i = 0; i < buffer.length; i++) {
                if (buffer[i] == null) {
                    buffer[i] = " data ";
                    System.out.println(Thread.currentThread().getName() + " produced " + buffer[i]);
                    break;
                }
            }
            getCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setSemaphore.release();
        }
    }

    public void get() {
        try {
            getSemaphore.acquire();
            lock.lock();
            while (isEmpty()) {
                getCondition.await();
            }
            for (int i = 0; i < buffer.length; i++) {
                if (buffer[i] != null) {
                    System.out.println(Thread.currentThread().getName() + " consumed " + buffer[i]);
                    buffer[i] = null;
                    break;
                }
            }
            setCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getSemaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerService service = new ProducerConsumerService();
        int num = 60;
        ThreadP[] threadP = new ThreadP[num];
        ThreadC[] threadC = new ThreadC[num];
        for (int i = 0; i < num; i++) {
            threadP[i] = new ThreadP(service);
            threadC[i] = new ThreadC(service);
        }
        Thread.sleep(2000);
        for (int i = 0; i < num; i++) {
            threadP[i].start();
            threadC[i].start();
        }
    }
}

class ThreadP extends Thread {
    private ProducerConsumerService service;

    public ThreadP(ProducerConsumerService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.set();
    }
}

class ThreadC extends Thread {
    private ProducerConsumerService service;

    public ThreadC(ProducerConsumerService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.get();
    }
}