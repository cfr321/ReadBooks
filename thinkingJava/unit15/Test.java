package thinkingJava.unit15;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/29 18:01
 * @Description:
 */
public class Test {

    public static  Comparable max1(List<Comparable> list) {
        Comparable max=list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (max.compareTo(list.get(i)) < 0) {
                max = list.get(i);
            }
        }
        return max;
    }
    public static <E extends Comparable<E>> E max2(List<E> list) {
        E max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (max.compareTo(list.get(i)) < 0) {
                max = list.get(i);
            }
        }
        return max;
    }
    public static <T extends Comparable<? super T>> T max3(List<? extends T> list) {
        T max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (max.compareTo(list.get(i)) < 0) {
                max = list.get(i);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        ArrayList<Comparable> c1s = new ArrayList<>(Arrays.asList(new C1(2), new C2(4)));
        c1s.add(new DD());
        max3(c1s);
    }
}
class DD implements Comparable<DD>{

    @Override
    public int compareTo(@NotNull DD o) {
        return 0;
    }
}
class C1 implements Comparable<C1> {
    private int size;

    public C1(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(@NotNull C1 o) {
        return size - o.size;
    }

    @Override
    public String toString() {
        return "size=" + size;
    }
}

class C2 extends C1 {

    public C2(int size) {
        super(size);
    }
}