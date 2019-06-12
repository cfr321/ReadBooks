package concurrentBook.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/9 18:55
 * @Description:
 */
public class LockSupportTest {
    public static void main(String[] args) {
        LockSupportTest lockSupportTest = new LockSupportTest();
        lockSupportTest.test1();

        // test2();
        //  test3();
        //test4();
    }

    //在pack的线程可以被打断而且不抛出异常
    private static void test4() {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                LockSupport.park();
                System.out.println("some thing");
            }
            System.out.println("child thread unpack");
        });
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }


    //可以唤起被挂起的线程
    private static void test3() {
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println("son unpack");
        });
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("parent make son unpack");
        LockSupport.unpark(thread);
    }

    //被unpack的线程将不会被挂起
    private static void test2() {
        LockSupport.unpark(Thread.currentThread());
        System.out.println("before pack");
        LockSupport.park();
        System.out.println("after pack");
    }

    //pack之后被挂起
    private  void test1() {
        System.out.println("before pack");
        LockSupport.park(this);
        System.out.println("after pack");
    }
}
