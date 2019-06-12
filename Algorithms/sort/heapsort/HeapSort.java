package Algorithms.sort.heapsort;

import Algorithms.sort.Sort;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/12 18:03
 * @Description:
 */
public class HeapSort extends Sort {
    public static void sort(Comparable[] arr) {
        if (arr.length > 1) {
            int len = arr.length;
            for (int i = len / 2 - 1; i >= 0; i--) {
                adjustHeap(arr, i, len);
            }
            for (int i = len-1; i >0 ; i--) {
                exchange(arr,0,i);
                adjustHeap(arr,0,i);
            }
        }
    }

    private static void adjustHeap(Comparable[] arr, int i, int len) {
        while (true) {
            int j = 2 * i + 1;
            if (j < len) {
                if (j + 1 < len && less(arr[j], arr[j + 1])) {
                    j++;
                }
                if (less(arr[i], arr[j])) {
                    exchange(arr, i, j);
                    i = j;
                    continue;
                }
            }
            return;
        }
    }
    private static void adjust(Comparable[] arr, int i, int len){
        int j=2*i+1;
        if(j<len){
            if(j+1<len && less(arr[j],arr[j+1])){
                j++;
            }
            if(less(arr[i],arr[j])){
                exchange(arr,i,j);
                adjust(arr,j,len);
            }
        }
    }
}
