package Algorithms.sort.mergesort;

import Algorithms.sort.Sort;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/7 20:18
 * @Description:
 */
public class MergeSort extends Sort {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
    public static void sort2(Comparable[] a){
        aux=new Comparable[a.length];
        int N=a.length;
        int help;
        for (int i = 1; i <N ; i=i+i) {
            help=i+i;
            for (int j = 0; j <N-i; j+=help) {
                merge(a,j,j+i-1,Math.min(N-1,j+help-1));
            }
        }
    }

    //自顶向下
    public static void sort(Comparable[] a, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        sort(a, l, m);
        sort(a, m + 1, r);
        merge(a, l, m, r);

    }

    private static void merge(Comparable[] a, int l, int m, int r) {
       // if(less(a[m],a[m+1])) return;
        for (int i = l; i <= r; i++) {
            aux[i] = a[i];
        }
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            if (i > m) a[k] = aux[j++];
            else if (j > r) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}
