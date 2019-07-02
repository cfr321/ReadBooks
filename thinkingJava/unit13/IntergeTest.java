package thinkingJava.unit13;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/26 18:46
 * @Description:
 */
public class IntergeTest {
    public static void main(String[] args) {
        //基本数据类型的常量池是  -128==>127，在这个范围的自动装箱会从常量池中取

        Long integer=127l;
        Long integer1=127l;
        Integer integer2=1; //在常量池
        Integer integer4=2;
        Integer integer5=3;
        Integer integer3=new Integer(1);  //指向堆

        System.out.println(integer5==integer2+integer4);

        System.out.println(integer2==integer3);
        System.out.println(integer==integer1);
    }
}
