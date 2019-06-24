package concurrentBook.lock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/12 16:20
 * @Description: StampedLock是不可重入的，提供了
 *                  写的独占锁，悲观共享读锁，乐观的读锁
 *                  乐观的读锁并不能保证数据的最新请求，
 *                  它还可以进行锁的升级，在乐观失败后升级为悲观，防止其他改
 *                  在悲观读锁想要修改数的时候也可以升级为写锁
 */
public class StampedLockTest {
    private double x,y;
    StampedLock lock=new StampedLock();

    double distanceFromOrigin(){
        long l = lock.tryOptimisticRead(); //无锁获取
        double currentX=x,currentY=y;
        if(lock.validate(l)){  //判断版本是否可用
            long l1 = lock.writeLock();
            try {
                currentX=x;
                currentY=y;
            }finally {
                lock.unlockRead(l1);
            }
        }
        return x*x+y*y;
    }

    public static void main(String[] args) {
        //This class is immutable and thread-safe.

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE;
        String format = timeFormatter.format(LocalDateTime.now());
        String[] split = format.split("-");

        System.out.println(format);
    }
}
