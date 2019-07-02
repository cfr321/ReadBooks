package thinkingJava.unit14;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/27 23:22
 * @Description:
 */
public class ShowMethods {
    private static Pattern pattern=Pattern.compile("\\w+\\.");
    public static void show(Class<?> c){
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println(pattern.matcher(method.toString()).replaceAll(""));
        }

    }

    public static void main(String[] args) {
        show(String.class);
    }
}
