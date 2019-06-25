package thinkingJava.unit10;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/24 16:31
 * @Description:
 */
public class Outer2 {
    static class Inner{
        private void fun(){
            System.out.println("inner de fun");

            // Outer2.this    编译错误
            Outer2 outer2 = new Outer2();
            outer2.fun2();
        }
    }

    private void fun2(){
        System.out.println("outer de fun");
    }

    public static void main(String[] args) {
        Inner inner = new Inner();
        inner.fun();
        System.exit(0);

    }
}
