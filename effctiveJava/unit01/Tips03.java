package effctiveJava.unit01;


import java.io.IOException;
import java.util.Properties;

import static effctiveJava.unit01.Test.INSTANCE;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 10:42
 * @Description: 用私有构造器或者枚举强化 Singleton属性
 */
public class Tips03 {
    private static boolean flag=true;
    private static final Tips03 TIPS_03=new Tips03();
    //构造方法只能被用一次，防止反射访问私有属性。
    //AccessibleObject、的子类有Filed，Function，Construct
    //AccessibleObject.setAccessible(fields, true);
    private Tips03 () {
        synchronized (Tips03.class){
            if (flag){
                flag=!flag;
            }else {
                throw new RuntimeException();
            }
        }
    }
    public static Tips03 getInstance(){
        return TIPS_03;
    }

    //防止序列化反序列破坏单例。
    private Object readResolve(){
        return TIPS_03;
    }
    public static void main(String[] args){
        System.out.println(INSTANCE.properties.getProperty("test"));
    }
}
enum Test{
    INSTANCE;
    Properties properties;
    {
        properties=new Properties();
        try {
            properties.load(Test.class.getClassLoader().getResourceAsStream("test.properties"));
            System.out.println("=======");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}