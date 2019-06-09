package effctiveJava.unit02;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/9 17:17
 * @Description:    覆盖equals时总要覆盖hashcode
 */
public class Tips11 {
    /**
     * hashcode是equals成立的充分不必要条件
     * 对象相等则hashcode一定相同，但是对象不相同他们的hashcode有可能相同（hash冲突）
     * 为了让基于散列的集合 hashMap和HashSet起作用的前提就是满足：覆盖equals时总要覆盖hashcode
     *
     * 我们的HashMap是更具对象的hashcode值来决定存放的位置和找到存放的位置，当改写了equals方法时两个
     * 地址不一样的对象相等，我们希望可以从hashMap中取出想要的value，这时候相等的对象却有着不一样的hashcode，
     * 使得无法取出对应的值。
     */
    public static void main(String[] args) {
        Object[] ints = {1,2,3};
        Object[] clone = ints.clone();
        clone[2]=0;
        System.out.println(ints[2]);
    }
}
