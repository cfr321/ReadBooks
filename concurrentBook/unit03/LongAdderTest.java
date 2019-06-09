package concurrentBook.unit03;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/8 19:16
 * @Description: LongAdder是AtomicLong的升级版本，在高并发下性能更好
 */
public class LongAdderTest {
    /**
     * LongAdder里面维持了一个Cell数组，来分散线程对数值的操作，
     * 线程进行增加减少是对Cell数组里面的一个Cell操作，这样就减少了线程的争抢。
     *
     * 1、Cell类：有一个volatile的value属性保证可见性，对value的更新用cas保证原子性，用@sun.misc.Contended避免了为共享
     * 2、longValue() 和sum()方法，将base和Cell数组里面的值累加到sum，返回，但是这个方法没有任何同步措施，并不是一个原子快照值
     * 3、reset()，如果Cell[]有值，将里面的元素的值变了0L
     * 4、add()，在cells为null，或者获取cas更新失败后，进行
     * 5、longAccumulate(x, null, uncontended);
     *      这个方法包含了cell[]的创建、扩容、元素的填充，选择访问的cell，以及对cell进行cas的更新。
     */

    //测试AtomicLong和LongAdder性能测试
    //好吧，在我的电脑上没有比较出两者的差别。。
    public static void main(String[] args) {
        AtomicLong atomicLong=new AtomicLong();     //100个线程 结果0.06-0.07  1000个0.18  10000个1.5
        LongAdder adder=new LongAdder();            //100个线程 结构0.06-0.07  1000个0.2   10000个1.5
        CountDownLatch countDownLatch=new CountDownLatch(10000);

        //自定义双目运算
        LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left * right, 10);
        longAccumulator.accumulate(10);
        System.out.println(longAccumulator.get());



        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    adder.increment();
                    //adder.add(5);
                   // atomicLong.incrementAndGet();
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stopwatch.elapsedTime()+"  "+adder.sum());

    }
}
