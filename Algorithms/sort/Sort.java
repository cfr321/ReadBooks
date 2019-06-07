package Algorithms.sort;

import Algorithms.sort.mergesort.MergeSort;

import java.util.Arrays;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 23:02
 * @Description:
 */
public  class Sort {
    protected static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    protected static void exchange(Comparable[] arr,int i,int j){
        Comparable temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    protected static void show(Comparable[] arr){
        System.out.println(Arrays.toString(arr));
    }
    public static boolean isSorted(Comparable[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            if(less(arr[i+1],arr[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] integers = {4, 6, 0,2,6,7,9,1,7, 1, 2};
        MergeSort.sort2(integers);
        MergeSort.show(integers);
    }
}
