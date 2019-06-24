package test;


/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/10 23:41
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        //return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        String string = Integer.toHexString(Integer.MAX_VALUE);
        System.out.println(string);
    }
}
