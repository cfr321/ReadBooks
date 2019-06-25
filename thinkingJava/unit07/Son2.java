package thinkingJava.unit07;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/23 23:38
 * @Description:
 *      Java对于代码的复用的方式有组合与继承，
 *      通常来讲我们应该优先考虑组合，继承最大的特性是它的同类性，可以实现多态
 *      在考虑用那种方式的时候我们应该考虑是不是有需要去使用多态的性质，也就是说有没有父子类的关系
 *
 *      关于多态：
 *         对于private本身就是final的，不会有继承
 *         属性也不会有多态
 *         对象的创建的过程会先去调用父类的构造器，如果父类的构造器调用了一个被子类覆盖的方法
 *         那这个方法是是调用了子类的，让后这个时候子类的属性还没有被初始化赋值
 *         这也就出现了下下面这个奇怪的问题
 */
public class Son2 extends Fa2{
    private int field=1;

    public Son2(int field) {
        this.field = field;

    }

    @Override
    public void draw() {
        System.out.println("son"+field); //输出的会是 0，因为这个方法是父类调用的
                                     // ，但是这个时候子类的属性还没有初始化
    }

    public static void main(String[] args) {
        new Son2(5);

    }
}

class Fa2{
    private int field=1;
    public Fa2() {
        System.out.println("begin");
        draw(); //调用的是子类的draw方法
        System.out.println("after");
    }

    public void draw() {
        System.out.println(field);
    }
}
interface Simple{
    int a=8;
    default void fun(){
        System.out.println(a);
    }
    public static void fun2(){
        System.out.println("static");
    }
}
class SimSon implements Simple{
    public static void main(String[] args) {
        SimSon simSon=new SimSon();
        simSon.fun();

    }
}