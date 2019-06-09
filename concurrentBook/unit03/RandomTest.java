package concurrentBook.unit03;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/8 18:33
 * @Description: ThreadLockRandom
 */
public class RandomTest {
    /**
     *     ThreadLockRandom的性能要高于Random
     *     Random是自旋，ThreadLockRandom是让种子变成线程级私有，这样也是线程安全的。
     *     测试来看Random需要0.110左右，ThreadLockRandom只要0.064
     * @param args
     */
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        threadLockRandom();
        System.out.println(stopwatch.elapsedTime());
    }

    private static void threadLockRandom() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        CountDownLatch downLatch=new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    System.out.println();
                    current.nextInt(100);
                }
                downLatch.countDown();
            }).start();
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void random1() {
        Random random=new Random();
        CountDownLatch downLatch=new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    random.nextInt(100);
                }
                downLatch.countDown();
            }).start();
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
