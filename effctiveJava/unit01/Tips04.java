package effctiveJava.unit01;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/7 12:22
 * @Description:    通过私有构造器强化不可实例化的能力
 */
public class Tips04 {

    //防止实例化
    private Tips04(){
        //通过反射也不行
        throw new AssertionError("改类不允许被实例化");
    }
    //防止序列化反序列破坏单例。
    private Object readResolve(){
        return null;
    }

}
//类的构造器私有化之后，它就不能被继承了。。。
//class Tips04Son extends Tips04{
//
//}