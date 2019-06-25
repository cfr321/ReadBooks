package thinkingJava.unit11;

import java.util.*;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/25 14:32
 * @Description:
 */
public class ModifyingArrayAsList {

    //Arrays.asList还是好用原来的数组作为List的数组，
    //   A不能改变大小，对他进行修改也会出现对原始数组的改变。
    public static void main(String[] args) {
        Random random = new Random(47);
        Integer[] arr = {1, 2, 3, 6, 5, 4, 9};
        List<Integer> integers = Arrays.asList(arr);
        List<Integer> list = new ArrayList<>(integers);

        Collections.shuffle(list,random);
        System.out.println(list);
        System.out.println(Arrays.toString(arr));


        Collections.shuffle(integers);
        System.out.println(Arrays.toString(arr));
        System.out.println(integers);
    }
}
