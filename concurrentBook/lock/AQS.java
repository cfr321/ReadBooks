package concurrentBook.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/10 16:07
 * @Description: 并发包锁的实现，AQS
 */
public class AQS implements Lock {

    /*
     AQS是锁的基础，里面又独占和共享两种模式，而ReentrantLock是它独占锁的实现
     ReentrantReadWriteLock是它独占和共享锁的实现。
     */
    /*
    线程四步就是一个线程尝试获取锁的不太仔细的展开，这是非公平的，公平的就不会有tryAcquire(arg)
    state是在AQS里面定义状态参数

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
    3、看看ReentranLock里面公平锁获取锁的操作
     protected final boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            //1、还没有线程获取锁
            if (c == 0) {
            //hasQueuedPredecessors()
            //AQS有等待的其他的线程，加上 ！就是没有等待的，才会下面的操作
            //非公平没有这个判断
                if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            //2、可重入
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
             //获取失败
            return false;
        }
    }
    4、加入队列的操作
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

    5、进入队列后检查自己的前面一个是不是到了可head，然后尝试获取锁
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

    /*
        AQS对条件得支持
        这个就是ConditionObject完成得，维持一个wait队列
     */

    /*
    ReentrantReadWriteLock实现读写分离。里面也是有一个Sync继承了AQS
    而里面得ReadLock和WriteLock对Sync是持有而不是继承，这里复合优先于继承。

     里面两把锁，带来得就有两个状态，而Doug Lea只用一个数记录了锁得状态，用32的int
     高位是读锁的数量。低位时写锁的状态，还是哪个state，只是不一样的味道

        获取锁的判断是，如果当前有写锁，则直接入队。
                        如果当前有读锁，则请求写锁的入队，请求读锁会请求锁。
                        如果没锁，就会去尝试获取锁。
         下面主要看看ReadLock的共享机制：

    1、调用lock，来到AQS里面的共享获取方法
    public final void acquireShared(int arg) {
        if (tryAcquireShared(arg) < 0)
            doAcquireShared(arg);
    }

    2、tryAcquireShared(arg)由Sync实现
    protected final int tryAcquireShared(int unused){
       Thread current = Thread.currentThread();
            int c = getState();
            //1、如果有写锁（独占锁）占用，且不是当前线程，返回-1
            if (exclusiveCount(c) != 0 &&
                getExclusiveOwnerThread() != current)
                return -1;

            //2、没有写锁，则可以尝试获取。
            int r = sharedCount(c);
            //这个readerShouldBlock()判断条件是有写锁在AQS列队下一个位置等待
            if (!readerShouldBlock() &&
                r < MAX_COUNT &&
                //这里的CAS是确保当多个线程请求读锁只有一个读锁获取成功。
                compareAndSetState(c, c + SHARED_UNIT)) {
                //3、读锁已经成功获取，后面只要进行一些参数的设置。
                if (r == 0) {
                    firstReader = current;
                    firstReaderHoldCount = 1;
                } else if (firstReader == current) {
                    firstReaderHoldCount++;
                } else {
                    HoldCounter rh = cachedHoldCounter;
                    if (rh == null || rh.tid != getThreadId(current))
                        cachedHoldCounter = rh = readHolds.get();
                    else if (rh.count == 0)
                        readHolds.set(rh);
                    rh.count++;
                }
                //读锁增加一定会1，不必要入队
                return 1;
            }
            //4、读锁竞争失败，则会自旋的取获取读锁。操作和这个方法差不多只是加上了自旋
            return fullTryAcquireShared(current);
          }

         3、如果读锁获取失败就入队doAcquireShared(arg)。
     */
    //下面实现用AQS实现一个不可重入的锁

    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            assert arg==1;
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg==1;
            if(getState()==0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    private final Sync sync=new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
