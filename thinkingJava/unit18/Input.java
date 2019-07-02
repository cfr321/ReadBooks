package thinkingJava.unit18;

import java.io.*;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/30 18:38
 * @Description:
 */
public class Input {
    public static final String filename = "src\\main\\java\\thinkingJava\\unit18\\DirList.java";

    public static String read(String filename, String rex) {
        Pattern compile = Pattern.compile(rex);
        LinkedList<String> list = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                if (compile.matcher(s).find()) {
                    list.offer(s);
                }
            }
            while (list.peek() != null) {
                builder.append(list.pollLast() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void dataWrote() {
        try (DataOutputStream outputStream =
                     new DataOutputStream(
                             new BufferedOutputStream(new FileOutputStream("hhh.out")))) {
             outputStream.writeUTF("你好\n");
             outputStream.writeDouble(123.2);
             outputStream.close();
             DataInputStream inputStream=new DataInputStream(new BufferedInputStream(new FileInputStream("hhh.out")));
            System.out.println(inputStream.readUTF());
            System.out.println(inputStream.readDouble());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static void fun(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename));
             PrintWriter writer = new PrintWriter("hhh.out")) {

            String s;
            long l = System.nanoTime();
            while ((s = reader.readLine()) != null) {
                writer.println(s);
            }
            System.out.println(System.nanoTime() - l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        dataWrote();
    }
}
