package thinkingJava.unit15;

import thinkingJava.util.Generator;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 17:04
 * @Description:
 */
public class Fibonacci implements Generator<Integer> {
    private Integer before=0;
    private Integer next=1;
    @Override
    public  Integer next() {
        next=next+before;
        before=next-before;

        return before;
    }

    private <T,V,M> void get(T t,V v,M m){}
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 10; i++) {
            System.out.println(fibonacci.next());
        }
    }
}
