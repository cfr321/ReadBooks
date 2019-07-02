package thinkingJava.pojo;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 16:40
 * @Description:
 */
public class Coffee {
    private static int count=0; //只属于父类
    private final int id=count++;   //但是子类创建父类的非静态会被调用一次。使得这个count在增加
    @Override
    public String toString() {
        return getClass().getSimpleName()+" "+id;
    }
}
