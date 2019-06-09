package Algorithms.sort.heapsort;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/9 20:16
 * @Description:    这是一个优先列队，堆堆，可小可大的堆堆
 */
public class MyPQ<T> implements Iterable<T> {
    private Object[] pq;
    private int size;
    private boolean maxHeap;

    public MyPQ(int initCapacity) {
        this(initCapacity, true);
    }

    public MyPQ(int initCapacity, boolean isMax) {
        this.pq = new Object[initCapacity];
        size = 0;
        this.maxHeap = isMax;
    }

    public MyPQ(boolean isMax) {
        this(1, isMax);
    }

    public MyPQ() {
        this(true);
    }

    public MyPQ(T[] arr, boolean isMax) {
        this.maxHeap = isMax;
        size = arr.length;
        this.pq = new Object[size];
        for (int i = 0; i < size; i++) {
            pq[i] = arr[i];
        }
        for (int i = size / 2 - 1; i >= 0; i--) {
            adjust(i);
        }
    }

    public MyPQ(T[] arr) {
        this(arr, true);
    }

    public void add(T v) {
        if (this.size == this.pq.length) {
            resize(2 * this.pq.length);
        }
        pq[size++] = v;
        swim(size - 1);
    }

    public T getMax() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return (T) this.pq[0];
    }

    public T delMax() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        T t = (T) this.pq[0];
        exchange(0, --size);
        adjust(0);
        this.pq[size] = null;
        if (size >= 1 && pq.length >= 4 * size) {
            resize(pq.length / 2);
        }
        return t;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private void swim(int k) {
        while (k > 0 && less((k - 1) / 2, k)) {
            exchange((k - 1) / 2, k);
            k = (k - 1) / 2;
        }
    }

    private void resize(int i) {
        this.pq = Arrays.copyOf(pq, i);
    }

    private void adjust(int i) {
        while (true) {
            if (2 * i + 1 < this.size) {
                int j = 2 * i + 1;
                if (j < size - 1 && less(j, j + 1)) {
                    ++j;
                }
                if (less(i, j)) {
                    exchange(i, j);
                    i = j;
                    continue;
                }
            }
            return;
        }
    }

    private void exchange(int i, int j) {
        Object temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean less(int i, int j) {
        if (this.maxHeap)
            return ((Comparable) this.pq[i]).compareTo(this.pq[j]) < 0;
        else
            return ((Comparable) this.pq[i]).compareTo(this.pq[j]) > 0;
    }


    private class MyPQIterator implements Iterator {
        private MyPQ<T> copy;

        public MyPQIterator() {
            copy = new MyPQ<>(size,maxHeap);
            for (int i = 0; i < size; i++) {
                copy.add((T) pq[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return copy.size > 0;
        }

        @Override
        public Object next() {
            return copy.delMax();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyPQIterator();
    }

    public static void main(String[] args) {
        MyPQ<Integer> integers = new MyPQ<>(false);
        integers.add(1);
        integers.add(2);
        integers.add(5);
        integers.add(10);
        integers.add(8);
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
