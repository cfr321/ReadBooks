package concurrentBook.unit01;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 13:27
 * @Description:    InheritableThreadLocal
 */
public class ThreadLockTest3 {
    /**
     * 因为ThreadLocal不具有线程的继承性，所有有了InheritableThreadLocal
     * 这也体现了线程的通信
     */
    private static ThreadLocal<String> threadLocal=new InheritableThreadLocal<>();
    private static ThreadLocal<String> threadLocal2=new InheritableThreadLocal<>();
    public static void main(String[] args){
        threadLocal.set("main come in");
        new Thread(() -> {
            threadLocal2.set("son come in");
            System.out.println("son thread:"+threadLocal.get());
            System.out.println(threadLocal2.get());
        }).start();
        System.out.println("main:"+threadLocal.get());
    }
}
