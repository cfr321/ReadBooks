package concurrentBook.unit01;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 13:25
 * @Description:
 */
public class ThreadLockTest2 {
    /**
     * 不支持继承的测试
     */
    private static ThreadLocal<String> threadLocal=new ThreadLocal<>();
    public static void main(String[] args){
        threadLocal.set("main come in");
        new Thread(() -> {
            System.out.println("son thread:"+threadLocal.get());
        }).start();
        System.out.println("main:"+threadLocal.get());
    }
}
