package thinkingJava.unit13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/27 11:15
 * @Description:
 */
public class Regx {
    /*
    runoo+b，可以匹配 runoob、runooob、runoooooob 等，+ 号代表前面的字符必须至少出现一次（1次或多次）。

    runoo*b，可以匹配 runob、runoob、runoooooob 等，* 号代表字符可以不出现，也可以出现一次或者多次（0次、或1次、或多次）。

    colou?r 可以匹配 color 或者 colour，? 问号代表前面的字符最多只可以出现一次（0次、或1次）。
     */
    public static void main(String[] args) {
        mach1();
    }

    private static void mach2(String s, String s2) {
        Pattern compile = Pattern.compile(s);
        Matcher matcher = compile.matcher(s2);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    private static void mach1() {
        Matcher ma = Pattern.compile("\\w+").matcher("Evenxing is ok");
        while (ma.find()) {
            System.out.println(ma.group()+"   ");
        }
        Pattern.compile("-?\\D");
        mach2("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+[aeiou]\\b", "Arline ate eight applesee");
        String s = "Java java  JAVA sdjas;kd ";

        StringBuffer buffer=new StringBuffer();
        Integer.parseInt(s);
        ma.appendReplacement(buffer, ma.group().toUpperCase());

    }
}
