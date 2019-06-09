package Algorithms.sort.quicksort;

import Algorithms.sort.Sort;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/7 00:11
 * @Description:
 */
public class QuickSort extends Sort {
    public  static void sort(Comparable[] arr){
        sortAll(arr,0,arr.length-1);
    }

    private static void sortAll(Comparable[] arr, int L, int R) {
        if(L<R){
            exchange(arr,(int)Math.random()*(R-L+1),R);
            int[] pos=postions(arr,L,R);
            sortAll(arr,L,pos[0]);
            sortAll(arr,pos[1],R);
        }
    }

    private static int[] postions(Comparable[] arr, int L, int R) {
        int pl=L-1;
        int pr=R;
        while (L<pr){
            if(less(arr[L],arr[R])){
                exchange(arr,++pl,L++);
            }else if(arr[L].compareTo(arr[R])>0){
                exchange(arr,--pr,L);
            }else {
                L++;
            }
        }
        exchange(arr,R,pr);
        return new int[]{pl,++pr};
    }
}
