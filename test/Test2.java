package test;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/13 21:18
 * @Description:
 */
public class Test2 {
    int a = 1;

     class A {
        int b = 3;
        public void fun() {
            System.out.println(Test2.this.a);
        }
    }

    public static void main(String[] args) {

    }
}
