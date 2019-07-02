package thinkingJava.unit15;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 16:07
 * @Description:
 */
public class Holder<T>{

    T t;
    public void setT(T t){

    }

    /*
       1、泛型被设计的一个重要作用就是确定容器储存对象的类别，用编译保证类型的正确。
       核心概念：告诉编译器我要使用什么类型，让编译器帮你处理一些细节

       所有在运行期这个泛型是没有任何信息的，无法获取确切的类型信息
       下面这些操作统统是不行的：
       arg instanceof T
       T t=new T()
       T[] ts=new T[]
       T.class

        那如果需要类型信息，我们就需要引入一个类型标签
        Class<T> type;
        public ArrayMaker(Class<T> type) {
            this.type = type;
        }


     */

    public static void main(String[] args) {
        Holder holder=new Holder();
    }
}

class Foo<T>{
    T t;

    public Foo(T t) {
        this.t = t;
    }
    public void fun(){
        if(t instanceof String){
            System.out.println(t.getClass().getName());
            System.out.println("ai m");
        }else {

            System.out.println(t.getClass().getName());
        }
    }

    public static void main(String[] args) {
        Foo<String> hhh = new Foo<>("hhh");
        hhh.fun();
    }
}
class ArrayMaker<T>{
    Class<T> type;

    public ArrayMaker(Class<T> type) {
        this.type = type;
    }
    T[] create(int size){
        System.out.println(type.getName());
        T[] o = (T[]) Array.newInstance(type, size);
        return o;
    }

    public static void main(String[] args) {
        ArrayMaker<String> maker = new ArrayMaker<>(String.class);
        String[] strings = maker.create(5);
        System.out.println(Arrays.toString(strings));
        Array.newInstance(String.class,5);

    }

}