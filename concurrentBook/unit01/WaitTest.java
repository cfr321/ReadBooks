package concurrentBook.unit01;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 12:07
 * @Description:    wait只会释放调用wait方法的锁，不会释放其他的锁
 */
public class WaitTest {
    private volatile static Object object1=new Object();
    private volatile static Object object2=new Object();
    public static void main(String[] args){
        new Thread(() -> {
            synchronized (object1){
                System.out.println(Thread.currentThread().getName()+" 获取了object1的锁");
                synchronized (object2){
                    System.out.println(Thread.currentThread().getName()+" 获取了object2的锁");
                    try {
                        object1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) { e.printStackTrace();}
            synchronized (object1){
                System.out.println(Thread.currentThread().getName()+" 获取了object1的锁");
                synchronized (object2){
                    System.out.println(Thread.currentThread().getName()+" 获取了object2的锁");
                    try {
                        object1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
