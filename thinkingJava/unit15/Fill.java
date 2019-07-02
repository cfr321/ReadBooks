package thinkingJava.unit15;

import thinkingJava.util.Generator;

import java.util.Collection;
import java.util.HashMap;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/29 20:33
 * @Description:
 */
interface Addable<T> {
    void add(T t);
}
class AddableAdapter<T> implements Addable<T>{

    private Collection<T> collection;

    public AddableAdapter(Collection<T> collection) {
        this.collection = collection;
    }

    @Override
    public void add(T t) {
        collection.add(t);

    }
}
class Adapter{
    public static <T> Addable<T> collectionAdapter(Collection<T> collection){
        return new AddableAdapter<>(collection);
    }
}
public class Fill {
    public static <T> void fill(Addable<T> addable, Class<? extends T> c, int size) {
        try {
            for (int i = 0; i < size; i++) {
                addable.add(c.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void fill(Addable<T> addable, Generator<? extends T> g, int size) {
        try {
            for (int i = 0; i < size; i++) {
                addable.add(g.next());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        HashMap<String,Integer> map=new HashMap<>();
        map.get(1);

    }
}
