package thinkingJava.unit13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/27 12:48
 * @Description:
 */
public class JGrep {
    private static List<String> read(){

        List<String> strings=null;
        try {
            Path path = Paths.get("JGrep.java");
            strings= Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
    public static void main(String[] args) {
        List<String> read = read();
        Pattern compile = Pattern.compile("\\b[Ssct]\\w+");
        Matcher matcher = compile.matcher("");
        int index=0;
        for (String s : read) {
            matcher.reset(s);
            while (matcher.find()){
                System.out.println(index++ +": "+matcher.group()+": "+matcher.start());
            }
        }
    }
}
