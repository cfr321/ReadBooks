package Algorithms.find.st;

import java.util.Arrays;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/12 20:22
 * @Description:
 */
public class SortST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int capacity = 10;
    private int size = 0;

    public SortST(int capacity) {
        this.capacity = capacity;
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public SortST() {
        this(10);
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int rank = rank(key);
        if (rank < size && keys[rank].equals(key))
            return values[rank];
        else
            return null;
    }

    public void put(Key key, Value value) {
        int l = rank(key);
        if (l < size && keys[l].equals(key))
            values[l] = value;
        if (size == capacity)
            growup();
        for (int i = size; i > l; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[l] = key;
        values[l] = value;
        size++;
    }

    public void delete(Key key) {
        int l = rank(key);
        if (l < size && keys[l].equals(key)) {
            for (int i = l; i < size - 1; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
            size--;
        }
    }

    public Key select(int k) {
        return keys[k];
    }


    private void growup() {
        capacity = capacity << 1;
        this.keys = Arrays.copyOf(this.keys, capacity);
        this.values = Arrays.copyOf(this.values, capacity);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int rank(Key key) {

        int l = 0, r = size - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int i = key.compareTo(keys[mid]);
            if (i > 0) l = mid + 1;
            else if (i < 0) r = mid - 1;
            else return mid;
        }
        //这个l一定是下一个比 key大位置
        return l;
    }
}
