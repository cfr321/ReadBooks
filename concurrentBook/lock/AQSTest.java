package concurrentBook.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/12 15:54
 * @Description:
 */
public class AQSTest {
    final  AQS lock=new AQS();
    final  Condition condition=lock.newCondition();
    final  Queue<String> queue=new LinkedList<>();
    final  int size=10;

    private void producer(String a){
        lock.lock();
        try {
            while (queue.size()==size){
                condition.await();
            }
            queue.add(a);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    private void consumer(){
        lock.lock();
        try {
            while (queue.isEmpty()){
                condition.await();
            }
            String poll = queue.poll();
            System.out.println(poll);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        AQSTest test=new AQSTest();

        new Thread(() -> {
            int a=100;
            while ((a--)>0){
                test.producer(a+"");
            }
        }).start();
        new Thread(() -> {
            int b=100;
            while ((b--)>0){
                test.consumer();
            }
        }).start();
    }
}
