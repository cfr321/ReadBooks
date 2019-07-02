package thinkingJava.unit15;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 21:32
 * @Description:
 */
interface Factory<T>{
    T create();
}
class Foo2<T>{
    T t;
    public <F extends Factory<T>> Foo2(F f) {
        t=f.create();
    }
}
class IntegerFactory implements Factory<Integer>{

    @Override
    public Integer create() {
        return new Integer((int) Math.random()*100);
    }
}
public class FactoryTest {
    public static void main(String[] args) {
        Foo2<Integer> integerFoo2 = new Foo2<>(new IntegerFactory());

    }
}
