package thinkingJava.unit15.unit17;

import java.util.WeakHashMap;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/30 15:25
 * @Description:
 */
public class WeakMapTest {
    public static void main(String[] args) {
        WeakHashMap<Key,Value> map=new WeakHashMap<>();
        Key[] keys =new Key[1000];
        for (int i = 0; i < 1000; i++) {
            Key key = new Key(i);
            Value value = new Value(i);
            if(i%3==0)
                keys[i]=key;
            map.put(key, value);
        }
        Key key = new Key(1001);
        Value value = new Value(1001);
        System.gc();
        System.out.println(map.get(keys[3]));
        System.out.println(map.get(key));
    }
}
class Elem{
    Integer var;

    public Elem(Integer var) {
        this.var = var;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Elem && ((Elem)o).var.equals(var);
    }

    @Override
    public int hashCode() {
        return var != null ? var.hashCode() : 0;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(getClass().getSimpleName()+var);
    }
}
class Key extends Elem{
    public Key(Integer var) {
        super(var);
    }
}
class Value extends Elem{

    public Value(Integer var) {
        super(var);
    }
}