package Algorithms.sort;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 23:26
 * @Description:
 */
public class InsertSort extends Sort{
    public static void sort(Comparable[] arr){
        for (int i = 1; i <arr.length ; i++) {
            Comparable tmp=arr[i];
            int j=i;
            while (j>=1 && less(tmp,arr[j-1])){
                arr[j]=arr[--j];
            }
            arr[j]=tmp;
        }
    }
}
