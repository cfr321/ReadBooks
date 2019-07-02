package thinkingJava.unit10;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/24 15:22
 * @Description:
 */
public class Outer {
    private int aa=1;

    private void fun(){
        System.out.println("outer unit05 fun");
    }
    class Inner{
        private int aa=2;

        private void test(){
            int aa=3;

            System.out.println(aa);     // 3
            System.out.println(this.aa); // 2
            System.out.println(Outer.this.aa);  //1

            fun();       // "inner unit05 fun"
            Outer.this.fun(); // "outer unit05 fun"

        }
        private void fun(){
            System.out.println("inner unit05 fun");
        }
    }
    {
        final Outer outer = new Outer();
        final Inner inner = outer.new Inner();
    }
}