package Algorithms.find.bt;

import java.util.Arrays;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/22 14:45
 * @Description:
 */
public class LineHash<K, V> {
    private Node<K, V>[] table;
    private int size;
    private int tableSize;

    private static class Node<K, V> {
        final K k;
        V v;

        @Override
        public String toString() {
            return "Node{" +
                    "k=" + k +
                    ", v=" + v +
                    '}';
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    private int hash(K k) {
        if (k == null) return 0;
        int hash = k.hashCode();
        return (hash ^ (hash >>> 16)) & (tableSize-1);
    }

    public LineHash(int tableSize) {
        if (tableSize % 16 != 0)
            throw new IllegalArgumentException("tableSize must equals 16*n");
        this.tableSize = tableSize;
        table = new Node[tableSize];
    }

    public LineHash() {
        this(16);
    }

    public void put(K key, V value) {
        if (size >= tableSize / 2)
            resize(2 * tableSize);
        int i;
        for (i = hash(key); table[i] != null; i = (i + 1) % tableSize) {
            if (table[i].k.equals(key)) {
                table[i].v = value;
                return;
            }
        }
        table[i] = new Node<>(key, value);
        size++;
    }

    public V get(K key) {
        for (int i = hash(key); table[i] != null; i = (i + 1) % tableSize) {
            if (table[i].k.equals(key))
                return table[i].v;
        }
        return null;
    }

    public void delete(K key) {
        int i = hash(key);
        while (table[i] != null && !table[i].k.equals(key)) {
            i = (i + 1) % tableSize;
        }
        if (table[i] == null) return;

        table[i] = null;
        i = (i + 1) % tableSize;
        while (table[i] != null) {
            K k = table[i].k;
            V v = table[i].v;
            table[i]=null;
            size--;
            put(k,v);
            i=(i+1)%tableSize;
        }
        size--;
        if(size>0 && size==tableSize/8)
            resize(tableSize/2);
    }

    public int size(){
        return size;
    }
    public int tableSize(){
        return tableSize;
    }

    @Override
    public String toString() {
        return "LineHash{" +
                "table=" + Arrays.toString(table) +
                '}';
    }

    private void resize(int i) {
        LineHash<K, V> copyHash = new LineHash<>(i);
        for (int j = 0; j < tableSize; j++) {
            if(table[j]!=null){
                copyHash.put(table[j].k,table[j].v);
            }
        }

        table=copyHash.table;
        tableSize=copyHash.tableSize;
    }

    public static void main(String[] args) {
        LineHash<String,Integer> map=new LineHash<>();

        for (int i = 0; i < 16; i++) {
            map.put(i+"aa",i);
//            System.out.println(map.size());
//            System.out.println(map.tableSize());
        }
        System.out.println(map);
        for (int i = 0; i < 16; i++) {
            System.out.println(map.get(i + "aa"));
        }
    }
}
