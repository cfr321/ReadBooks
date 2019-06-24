package concurrentBook.blockQueue;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/21 10:57
 * @Description:
 */
public class DelayTest {
    static class DelayEle implements Delayed{
        private final long delayTime;
        private final long expire;
        private String takeName;

        public DelayEle(long delayTime, String takeName) {
            this.delayTime = delayTime;
            expire=System.currentTimeMillis()+delayTime;
            this.takeName = takeName;
        }

        /**
         * 还需要推迟的时间
         * @param unit
         * @return
         */
        @Override
        public long getDelay(@NotNull TimeUnit unit) {
            return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(@NotNull Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "DelayEle{" +
                    "delayTime=" + delayTime +
                    ", expire=" + expire +
                    ", takeName='" + takeName + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayEle> delayQueue=new DelayQueue();
        Random random=new Random();
        for (int i = 0; i < 10; i++) {
            DelayEle delayEle = new DelayEle(random.nextInt(500), "take" + i);
            delayQueue.offer(delayEle);
        }
        DelayEle take=null;
        while ((take = delayQueue.take())!=null){
            System.out.println(take);
        }
    }
}
