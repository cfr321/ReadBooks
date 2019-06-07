package concurrentBook.unit02;

import edu.princeton.cs.algs4.StdOut;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 18:45
 * @Description: Unsafe类并不能让开发人员使用，它可以操作内内存
 */
public class UnsafeTest {
    private static Unsafe unsafe;
    private static Unsafe unsafe1;
    //记录Unsafe state属性的偏移地址
    private static long stateOffset;
    private volatile long state;
    //这样会失败
//    static {
//        try {
//            stateOffset=unsafe.objectFieldOffset(UnsafeTest.class.getField("state"));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//    }
    static {
        try {
            //获取反射
            Class<Unsafe> unsafeClass = Unsafe.class;

            //通过他的theUnsafe属性获取
            Field field = unsafeClass.getDeclaredField("theUnsafe");

           // AccessibleObject.setAccessible(new Field[]{field},true);
            field.setAccessible(true);
            unsafe= (Unsafe) field.get(null);
            stateOffset=unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        UnsafeTest test=new UnsafeTest();
        StdOut.println("hello");
        unsafe.compareAndSwapLong(test,stateOffset,0,1);
        System.out.println("ok");

    }
}
