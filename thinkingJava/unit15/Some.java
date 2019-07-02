package thinkingJava.unit15;

import thinkingJava.util.Generator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/29 10:52
 * @Description:
 */
public class Some<T> {
    private void fun(List<String> ...somes){
        Object[] objects=somes;
        objects[0]=1L;
        somes[0].get(0);
    }
    public static void main(String[] args) {
        //数组的协变性，数组是不可变得
        Object[] objects=new String[10];//is ok,but objects is a stringArrays,do not put other here

       // List<Object> list=new ArrayList<String>();
        //上面这个是不行的，因为List<String>并不是List<Object>的子类，

        //这又是可以的，通配符运行你建立某一个群体类型的集合。
        //通配符不应该用来定义类型，而应该作为AIP的条件限制。

        List<? extends Object> list=new ArrayList<>();

        //确定的唯一的，也是任意的，但是先去我没办法确定下来。
        //


        List<Object> list2=new ArrayList();
        list2.add(1);
        list2.add("11");
    // List<String>[] a=new ArrayList<String>[10]; 数组是确定的，不能创建泛型数组
        Object o = list.get(1);
    }
    void fun(List<? extends List> list){
        List list1 = list.get(1);
    }
    <T> void fun2(List<T> list){
        T t = list.get(2);
    }
}
class Bla1 implements Generator<String>{

    @Override
    public String next() {
        return null;
    }
}


