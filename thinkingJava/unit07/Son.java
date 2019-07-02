package thinkingJava.unit07;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/23 21:39
 * @Description:
 */
public class Son extends Fa{
    public int field=1;
    static final void test(Fa fa){
        fa.fun();
    }
    @Override
    public int getField(){
        return field;
    }

    //静态没有多态
    public static void staticTest(){
        System.out.println("Fa unit05 static");
    }

    public static void main(String[] args) {
        Son son = new Son();
        test(son);

        Fa fa=new Son();
        System.out.println(fa.field+"    "+fa.getField());      //0   1 属性不会动态绑定，不是多态的


    }
}
class Fa{
    public int field=ss();

    public Fa() {
        System.out.println("Fa unit05 construct");
    }

    void fun(){
        System.out.println("aa");
    }
    public int getField(){
        return field;
    }
    int ss(){
        System.out.println("lalala");
        return 0;
    }
    //私有方法不会被继承
    private void fun2(){
        System.out.println("some thing");
    }
    public static void staticTest(){
        System.out.println("Fa unit05 static");
    }
}
