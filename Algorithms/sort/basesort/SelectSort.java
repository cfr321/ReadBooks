package Algorithms.sort;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 23:11
 * @Description:   选择排序
 */
public class SelectSort extends Sort {
    public  static void sort(Comparable[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int min=i;
            for (int j = i+1; j <arr.length ; j++) {
                if (less(arr[j],arr[min])) min=j;
            }
            exchange(arr,i,min);
        }
    }
}
