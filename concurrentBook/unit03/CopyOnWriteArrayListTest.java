package concurrentBook.unit03;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/9 18:16
 * @Description: CopyOnWriteArray
 */
public class CopyOnWriteArrayListTest {
    /**
     * 这是一个可以在线程进行修改操作的时候其他线程也能读取的线程安全的List
     *
     * 对于数组的修改操纵 增删改 都是类似下面的操作，先复制，然后修改，在合并会去。
     * final ReentrantLock lock = this.lock;
     *         lock.lock();
     *         try {
     *             Object[] elements = getArray();
     *             int len = elements.length;
     *             Object[] newElements = Arrays.copyOf(elements, len + 1);
     *             newElements[len] = e;
     *             setArray(newElements);
     *             return true;
     *         } finally {
     *             lock.unlock();
     *      }
     *
     * 但是这个的取和迭代是没有保证同步的，所以存在弱一致性问题
     * 因为进行增删改后是吧新的数组重新放回list，这样迭代器拿到的list就是一个旧版本的快照，
     * 修改对这个迭代器是不可见的
     */
    //验证弱一致性。
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();//默认容量是 0,而其只能是零
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        Iterator<String> iterator = list.iterator();
        Thread thread = new Thread(() -> list.add("ddd"));
        thread.start();
        thread.join();

        while (iterator.hasNext()){
            list.add("ddd");
            System.out.println(iterator.next());
        }
    }

}
