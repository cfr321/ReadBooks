package concurrentBook.unit03;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/9 19:28
 * @Description:
 */
public class FIFOMutex {
    private final Queue<Thread> queue = new ConcurrentLinkedQueue<>();
    private final AtomicBoolean isLock = new AtomicBoolean(false);

    public void lock() {
        Thread thread = Thread.currentThread();
        queue.add(thread);
        boolean wasInterrupted=false;
        while (queue.peek()!=thread || !isLock.compareAndSet(false,true))
        {
            LockSupport.park();
            //忽略终端
            if(Thread.interrupted()){
                wasInterrupted=true;
            }
        }
        queue.remove();
        //最后再处理中断
        if(wasInterrupted)
            thread.interrupt();
    }
    public void unlock(){
        isLock.set(false);
        //唤醒列顶的线程
        LockSupport.unpark(queue.peek());
    }

    public static void main(String[] args) {
        //保证了先进先出的锁
        FIFOMutex lock=new FIFOMutex();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                lock.unlock();
            }).start();
        }
    }
}
