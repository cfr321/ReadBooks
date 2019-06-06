package concurrentBook.unit01;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 13:06
 * @Description: ThreadLock
 */
public class ThreadLockTest {
    /**
     * 每一个线程都有一个  ThreadLocal.ThreadLocalMap threadLocals = null;
     * ThreadLocal用过对各个线程的这个Map进行操纵实现线程对资源的私有定制化
     */
    static void print(){
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }
    private static ThreadLocal<String> threadLocal=new ThreadLocal<>();
    public static void main(String[] args){
        for (int i = 0; i <2 ; i++) {
            new Thread(() -> {
                threadLocal.set(Thread.currentThread().getName()+"have a test");
                print();
            }).start();
        }
    }
}
