package thinkingJava.unit06;

import java.lang.reflect.Constructor;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/23 14:43
 * @Description:
 */
public class SingleTest {
    public static void main(String[] args) throws Exception {
        Class<SingleClass> singleClassClass = SingleClass.class;
        Constructor<SingleClass> constructor = singleClassClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingleClass singleClass = constructor.newInstance();
        SingleClass singleClass1 = constructor.newInstance();
        System.out.println(singleClass);
        System.out.println(singleClass1);
        singleClass.fun();
    }

}
class SingleClass{
    private static SingleClass singleClass=new SingleClass();
    private SingleClass(){
    }
    public static SingleClass getInstance(){
        return singleClass;
    }
    public void fun(){
        System.out.println("hhhh");
    }
}