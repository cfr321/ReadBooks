package effctiveJava.unit04;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/15 10:21
 * @Description: 优先考虑泛型方法
 */
public class Tips29 {
    public static <E> Set<E> union(Set<E> set1,Set<E> set2){
        Set<E> set = new HashSet<>(set1);
        set.addAll(set2);
        return set;
    }

    public static void main(String[] args) {


    }
}
