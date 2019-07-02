package thinkingJava.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 16:30
 * @Description:
 */
public class RandomList<E> extends ArrayList<E> {
    private Random random=new Random(47);
    public E select(){
        return get(random.nextInt(size()));
    }
}
