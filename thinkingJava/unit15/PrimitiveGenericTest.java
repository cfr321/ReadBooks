package thinkingJava.unit15;


import thinkingJava.util.Generator;
import thinkingJava.util.RandomData;

import java.util.Arrays;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/29 13:12
 * @Description:
 */
class FArray{

    public static <T> T[] fill(T[] ts, Generator<T> generator){
        for (int i = 0; i < ts.length; i++) {
            ts[i]=generator.next();
        }
        return ts;
    }

}
public class PrimitiveGenericTest {
    public static void main(String[] args) {
        String[] fill = FArray.fill(new String[7], RandomData.string(9));
        System.out.println(Arrays.toString(fill));
        java.lang.Integer[] integers = FArray.fill(new java.lang.Integer[10], RandomData.integer());
        System.out.println(Arrays.toString(integers ));

    }
}
class A<T>{
    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
class B extends A<B>{

}

class D<T extends D<T>>{ }

class F<T extends F<T>>{
    F f;


    public static void main(String[] args) {
        F<M> ff = new F<>();
    }
}
class M extends F<M>{

}
