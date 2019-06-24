package thinkingJava.unit05;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/21 15:59
 * @Description:
 */
public class InitialValue {
    boolean a;
    char c;
    int b;
    double d;
    float f;
    short s;
    byte t;
    StringBuilder builder;
    private void fun(){
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(f);
        System.out.println(s);
        System.out.println(t);
        System.out.println(builder);
    }
    public static void main(String[] args) {
        InitialValue initialValue = new InitialValue();
        initialValue.fun();
    }
}
