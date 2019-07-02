package thinkingJava.util;

import java.util.Collection;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 19:47
 * @Description:
 */
public class Generators {
    public static <T> Collection<T> fill(Collection c,Generator<T> g,int n){
        for (int i = 0; i < n; i++) {
            c.add(g.next());
        }
        return c;
    }
}
