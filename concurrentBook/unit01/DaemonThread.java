package concurrentBook.unit01;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 12:57
 * @Description: 用户线程和守护线程
 */
public class DaemonThread {
    /**
     * JVM不会等待一个守护线程结束，它只会等待用户线程结束
     */
    public static void main(String[] args){
        Thread thread=new Thread(()->{
           while(true){
               System.out.println("i am alive");
           }
        });

        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over,  u as a daemon so thread JVM never care about u");
    }
}
