package concurrentBook.unit02;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/7 13:47
 * @Description: volatile来防止指令重拍
 */
public class VolatileNoSort extends Thread{
    /**
     * volatile保证了内存的可见性，除此之外它还有防止指令重拍的效果
     *
     * volatile写操作前的不能重拍到后面，volatile读操作后面的不能重拍到前面。
     */
    private static boolean ready=false;
    private static int sum=0;
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                if(ready){
                    System.out.println(sum+sum); //这个也会被刷新
                }
            }
        }
    public static class WriteThread extends Thread{
        @Override
        public void run() {
            sum=2;
            ready=true;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        VolatileNoSort volatileNoSort = new VolatileNoSort();
        volatileNoSort.start();
        try { TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) { e.printStackTrace();}
        Thread thread= new WriteThread();
        thread.start();
        Thread.sleep(10);
        volatileNoSort.interrupt();
    }
}
