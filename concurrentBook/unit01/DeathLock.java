package concurrentBook.unit01;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 12:38
 * @Description:
 */
public class DeathLock {
    private static Object o1=new Object();
    private static Object o2=new Object();
    public static void main(String[] args){
        new Thread(() -> {
            synchronized (o1){
                System.out.println( "A take o1");
                try { TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) { e.printStackTrace();}
                synchronized (o2){
                    System.out.println("A take o2");
                }
            }
        }, "A").start();
        new Thread(() -> {
            synchronized (o2){
                System.out.println( "B take o1");
                try { TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) { e.printStackTrace();}
                synchronized (o1){
                    System.out.println("B take o2");
                }
            }
        }, "B").start();
    }
}
