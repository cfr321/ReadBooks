package thinkingJava.unit05;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/22 21:01
 * @Description:
 */
public class DynamicArray {
    static void print(int required,String ...strings){
        System.out.println("fi");
    }
    static void print(String ... strings){
        System.out.println("se");
    }
    public static void main(String[] args) {
        //Other.main(new String[]{"fiddle","unit05","dum"});
        Spiciness[] values = Spiciness.values();
    }
}
class Other{
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg+" ");
        }
    }
}
enum Spiciness{
    NOT,MILD,MEDIUM
}
