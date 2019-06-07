package Algorithms.sort.basesort;

import Algorithms.sort.Sort;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 23:47
 * @Description:
 */
public class BubbleSort extends Sort {
    public static void sort(Comparable[] arr){
        if(arr.length>1){
            for (int i = 1; i < arr.length; i++) {
                for (int j = 0; j <arr.length-i ; j++) {
                    if(less(arr[j+1],arr[j]))
                        exchange(arr,j+1,j);
                }
            }
        }
    }
}
