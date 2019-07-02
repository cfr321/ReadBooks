package thinkingJava.unit14;

import java.lang.reflect.Field;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 12:21
 * @Description:
 */
public class Single {
    private static final Single SINGLE=new Single();
    private Single() {
        synchronized (Single.class){
            if(SINGLE==null){
                System.out.println("fist do new single one");
            }else {
                throw new RuntimeException(new IllegalAccessException("no don't do that"));
            }
        }
    }
    public static Single getInstance(){
        return SINGLE;
    }
    private synchronized static void fun1(){
        System.out.println("fun1 ====");
    }
    private synchronized void fun2(){
        System.out.println("fun2 ====");
    }

    public static void main(String[] args) throws Exception {
        Single instance = Single.getInstance();
        Class<Single> singleClass = Single.class;
        Field declaredField = singleClass.getDeclaredField("SINGLE");
        declaredField.setAccessible(true);
        declaredField.set(instance,null);
        Single single = singleClass.newInstance();
//        System.out.println(instance);
//        System.out.println(single);
    }

}
