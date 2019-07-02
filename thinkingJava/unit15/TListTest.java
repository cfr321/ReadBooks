package thinkingJava.unit15;

import java.util.ArrayList;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 21:55
 * @Description:
 */
public class TListTest<T> {
    /*
    我们无法创建泛型的数组，只能用Object数组来保存所有的对象。
    但是Object数组终归是Object数组，虽然里里面存的是T类型的对象，但是它不是T类型的数组
     */
    T[] t;
    Object[] objects;
    public TListTest() {
        t= (T[]) new Object[10];
        objects=new Object[10];
    }

    T[] getObjects(){
        return (T[]) objects;
    }
    T[] getTs(){
        return t;
    }

    public static void main(String[] args) {
        Object[] objects=new String[10];
        ArrayList arrayList = new ArrayList();

    }
}
