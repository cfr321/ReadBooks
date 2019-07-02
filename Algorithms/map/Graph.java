package Algorithms.map;

import edu.princeton.cs.algs4.Bag;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/29 22:40
 * @Description:
 */
public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int v) {
        V = v;
        adj=(Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i]=new Bag<Integer>();
        }
    }

    public void addEdge(Integer e1, Integer e2){
        adj[e1].add(e2);
        adj[e2].add(e1);
        E++;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "V=" + V +
                ", E=" + E +
                ", adj=" + Arrays.toString(adj) +
                '}';
    }

    public static void main(String[] args) {
        ArrayList<String>[] arrayLists= new ArrayList[10];
        Integer integer=3;
        arrayLists[1]=new ArrayList<String>();
        Object[] array=arrayLists;
        array[1]=new ArrayList<Integer>();
        Object[] ax=new Object[10];
        ax[0]=integer.intValue();
    }
}
