package Algorithms.sort;

import Algorithms.sort.basesort.BubbleSort;
import Algorithms.sort.basesort.InsertSort;
import Algorithms.sort.basesort.SelectSort;
import Algorithms.sort.heapsort.HeapSort;
import Algorithms.sort.mergesort.MergeSort;
import Algorithms.sort.quicksort.QuickSort;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 23:58
 * @Description:
 */
public class SortCompare {
    public static double time(String sortName,Double[] a){
        Stopwatch timer=new Stopwatch();
        if(sortName.equals("Insert")) InsertSort.sort(a);
        if(sortName.equals("Select")) SelectSort.sort(a);
        if(sortName.equals("Bubble")) BubbleSort.sort(a);
        if(sortName.equals("Quick")) QuickSort.sort(a);
        if(sortName.equals("Merge")) MergeSort.sort2(a);
        if(sortName.equals("Heap")) HeapSort.sort(a);
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg,int N,int T){
        double total=0.0;
        Double[] doubles = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                doubles[j]= StdRandom.uniform();
            }
            total+=time(alg,doubles);
        }
        return total;
    }

    /**
     * Bubble: 0.24000000000000019
     * Select: 0.14700000000000008
     * Insert: 0.11300000000000004
     *
     * Merge:  0.04800000000000003
     * Quick:  0.04200000000000002
     * Heap:   0.04   Heap: 0.03  Heap: 0.02 //最快。。。
     * @param args
     */
    public static void main(String[] args) {
        double insert = timeRandomInput("Quick", 1000, 100);
        System.out.println("Quick: "+insert);
    }
}
