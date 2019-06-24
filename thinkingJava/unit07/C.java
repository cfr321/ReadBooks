package thinkingJava.unit07;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/23 15:08
 * @Description:
 */
public class C extends A{
    private B b=new B();

    public C() {
        System.out.println("CCC");
    }

    public static void main(String[] args) {
        new C();
    }
}

class A{
    private B b=new B();
    public A() {
        System.out.println("AAA");
    }
}
class B {
    public B() {
        System.out.println("BBB");
    }
}