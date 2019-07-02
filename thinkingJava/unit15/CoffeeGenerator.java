package thinkingJava.unit15;

import org.jetbrains.annotations.NotNull;
import thinkingJava.pojo.Cappuccino;
import thinkingJava.pojo.Coffee;
import thinkingJava.pojo.Mocha;
import thinkingJava.util.Generator;

import java.util.Iterator;
import java.util.Random;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 16:49
 * @Description:
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class[] types = {Coffee.class, Cappuccino.class, Mocha.class};
    private Random random = new Random(47);
    private int count;

    public CoffeeGenerator(int count) {
        this.count = count;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee) types[random.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Iterator<Coffee> iterator() {
        final int size[] = {count};
        return new Iterator<Coffee>() {
            @Override
            public boolean hasNext() {
                return size[0] >0;
            }

            @Override
            public Coffee next() {
                size[0]--;
                return CoffeeGenerator.this.next();
            }
        };
    }

    public static void main(String[] args) {
        CoffeeGenerator coffees = new CoffeeGenerator(3);
        Iterator<Coffee> iterator = coffees.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
