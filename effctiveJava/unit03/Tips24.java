package effctiveJava.unit03;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/13 21:47
 * @Description: 优先使用静态内部类，而不是非静态内部类
 */
public class Tips24 {
    /**
     * 1、非静态的内部的对象都有一个于一个外围实例关联，这个
     *     关联的关系是要消耗空间的，想想在一个集合里面，他的节点
     *     都有一个外围实例的关联，这是需要很大空间的，这就会使得内存溢出。
     *
     *   对于对外围资源没有应用需要的，我们应该用静态内部类。
     */
    //展示一下非静态内部的属性访问
    int a=1;
    class A{
        int a=2;
        void fun(){
            int a=3;
            System.out.println(a);
            System.out.println(this.a);
            System.out.println(Tips24.this.a);
        }
    }

    public static void main(String[] args) {
        Tips24 tips24 = new Tips24();
        A a = tips24.new A();//非静态的需要对象访问
        a.fun();
    }
}
