package thinkingJava.unit11;

import java.util.*;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/25 13:33
 * @Description:
 */
public class ListIterator{
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        java.util.ListIterator<String> iterator = strings.listIterator();
        for (String string : strings) {

        }
        Map<Integer,String> map=new HashMap<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            entry.getKey();
        }
        Collections.shuffle(strings,new Random(47));
    }
}
