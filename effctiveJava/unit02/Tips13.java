package effctiveJava.unit02;

import java.util.Arrays;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/10 21:31
 * @Description: 谨慎的覆盖clone
 */
public class Tips13{
    /**
     * clone方法如果是简单的对象，他的域没有其他对象的引用，是可以直接调用super.clone的
     *
     * 但是如果有其他对象的域，简单的clone出来的结果的域与原对象的是同一个东西。
     */
    public static void main(String[] args) {
        //对于A我们没有取实现clone，看看会发生什么
        A a = new A(new int[]{1, 3, 5, 6});
        A clone = a.clone();
        int[] arr = clone.getArr();
        arr[1]=0;arr[2]=0;
        int[] arr1 = a.getArr();
        System.out.println(Arrays.toString(arr1));  //[1, 0, 0, 6] 原来的对象的值也被改变了

    }
}
class A implements Cloneable{
    private int[] arr;

    public A(int[] arr) {
        this.arr = arr;
    }

    public int[] getArr() {
        return arr;
    }

    @Override
    public A clone() {
        A clone = null;
        try {
            clone= (A) super.clone();
            clone.arr=this.arr.clone(); //加上这句话就可以了
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
