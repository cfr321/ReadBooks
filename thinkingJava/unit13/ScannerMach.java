package thinkingJava.unit13;

import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/27 14:49
 * @Description:
 */
public class ScannerMach {
    public static void main(String[] args) {
        StringReader stringReader = new StringReader("123.123.123@02/02/02\n" +"ad\n"+
                "123.123.123@02/02/03\n");
        Scanner scanner=new Scanner(stringReader);
        Pattern compile = Pattern.compile("(\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{2})");
        while (scanner.hasNext()){
            String next = scanner.next();
            Matcher matcher = compile.matcher(next);
            if (matcher.matches()) {
                String ip = matcher.group(1);
                String date = matcher.group(2);
                System.out.format("Thread on %s from %s \n",date,ip);
            }
        }
        try {
            Class.forName("thinkingJava.unit13.ScannerMach");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
