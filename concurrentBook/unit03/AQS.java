package concurrentBook.unit03;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/10 16:07
 * @Description: 并发包锁的实现，AQS
 */
public class AQS {

    /*
    AQS是锁的基础，里面又独占和共享两种模式，而ReentrantLock是它独占锁的实现
     ReentrantReadWriteLock是它独占和共享锁的实现。
     */
    /*
    线程四步就是一个线程尝试获取锁的不太仔细的展开，这是非公平的，公平的就不会有tryAcquire(arg)


    1、lock（）实际是调用ReentrantLock里面公平锁或者非公平锁的lock，这两把锁继承子AQS
    公平和非公平差别就是下面方法会不会直接去 compareAndSetState(0, 1)（非公平会公平的没有）

    final void lock() {
            if (compareAndSetState(0, 1))
                setExclusiveOwnerThread(Thread.currentThread());
            else
                acquire(1);
        }


    2、进入AQS的acquire(1) 尝试获取锁，不成功就加入队列，并且会LockSupport.pack自己
    public final void acquire(int arg) {
        if (!tryAcquire(arg) && //先尝试获取锁不成功，这个方法子类实现
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))  //才进入队列
            selfInterrupt();
    }

    3、加入队列的操作
    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on failure
        Node pred = tail;
        if (pred != null) {
            node.prev = pred;
            //尝试进去，成功直接返回
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        //如果前面没有返回，则循环的加入队列,知道成功才会返回
        enq(node);
        return node;
    }

    4、进入队列后检查自己的前面一个是不是到了可head，然后尝试获取锁
       还有有个打断状态的保存，见FIFOMutex

      如果获取不成功，前的节点也不是head节点，还会尝试pack当前线程，不去使劲的自旋获取锁。
        try {
                boolean interrupted = false;
                for (;;) {
                    final Node p = node.predecessor();
                    if (p == head && tryAcquire(arg)) {
                        setHead(node);
                        p.next = null; // help GC
                        failed = false;
                        return interrupted;
                    }

                    //尝试pack当前线程，避免自旋的资源浪费。
                    if (shouldParkAfterFailedAcquire(p, node) &&
                        parkAndCheckInterrupt())
                        interrupted = true;
                }
            }
     */

    /*
    然后是unlock(),它也是考AQS实现，

    1、首先是靠AQS的release（）来释放锁
    public final boolean release(int arg) {
        if (tryRelease(arg)) {  //tryRelease()同样需要靠子类实现
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h); //让队列中的下一个节点唤醒
            return true;
        }
        return false;
    }

    2、tryRelease(arg)，释放锁，在ReentrantLock里面的Syn实现（Syn继承了AQS)
        这个方法就是把一些状态参数调整
        protected final boolean tryRelease(int releases) {
            int c = getState() - releases; //可重入锁的体现，锁了多少层就要开多少次锁
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

      3、unparkSuccessor(h)就是将下一个节点唤起。
           waitStatus有四种状态
           1：被删除的
           -1：等待unpack的
           -2：waiting on condition
           -3:共享锁有关的

        Node s = node.next;
        if (s == null || s.waitStatus > 0) {    //如果当前节点被标记为删除的
            s = null;
            //从后往前找到最前面的一个状态小于0的？
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);   //unpark  这个节点

        这时候被唤起的节点就会又尝试取获取锁了，在前面获取锁的操作里面4步骤是没有退出的
        当被unpack之后，就又会去获取锁。
     */

}
