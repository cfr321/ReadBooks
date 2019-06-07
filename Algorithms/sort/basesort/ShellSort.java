package Algorithms.sort;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/7 00:43
 * @Description:
 */
public class ShellSort extends Sort{
    public  static void sort(Comparable[] arr){
        int N=arr.length;
        int h=1;
        while (h<N/3) h=3*h+1;
        while (h>=1){
            for (int i = h; i <arr.length ; i++) {
                Comparable tmp=arr[i];
                int j=i;
                while (j>=h && less(tmp,arr[j-h])){
                    arr[j]=arr[j-h];
                    j-=h;
                }
                arr[j]=tmp;
            }
            h/=3;
        }
    }
}
