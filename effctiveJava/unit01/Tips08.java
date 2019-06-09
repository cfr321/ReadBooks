package effctiveJava.unit01;


/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/8 15:55
 * @Description: 避免使用终结方法和清除方法
 */
public class Tips08 {
    /**
     * finalizer 和cleaner通常是不可预测的、也是危险的，一般情况也是不必要的
     * <p>
     * 对于需要处理的对象，我们应改实现AutoCloseable,用try with来处理来关闭清理。
     * 我们以一个使用完后需要打理的Room为例
     */
    public static void main(String[] args) {
//       try (Room room = new Room(15)) {
//            System.out.println("GoodBy");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}


/*
class Room implements AutoCloseable {

    //打扫着，它里面也有引用的实例
    private static final Cleaner cleaner = Cleaner.create();


    //资源关闭的处理，不能应用Room
    private static class State implements Runnable {
        int numJunk;

        State(int numJunk) {
            this.numJunk = numJunk;
        }

        @Override
        public void run() {
            numJunk = 0;
            System.out.println("打扫房间了，垃圾数量为：" + numJunk);
        }
    }

    private static State state;
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunk) {
        state = new State(numJunk);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}
 */