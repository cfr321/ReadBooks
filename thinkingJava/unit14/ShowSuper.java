package thinkingJava.unit14;

import java.util.ArrayList;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/27 16:29
 * @Description:
 */
class Initable{
    public static  int staticFinal=47;
    static {
        System.out.println("Initable init ");
    }
}
public class ShowSuper {

    public static void main(String[] args) {
        ArrayList<Integer> integers=new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        Class<? extends ArrayList> aClass = integers.getClass();
        Class<? extends ArrayList> aClass1 = strings.getClass();

        System.out.println(aClass.getName());
        System.out.println(aClass1.getName());


        // System.out.println(Initable.staticFinal);
        try {
            Class.forName("thinkingJava.unit14.Initable");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
