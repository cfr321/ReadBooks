package thinkingJava.unit18;

import java.io.*;
import java.util.Scanner;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/30 21:26
 * @Description:
 */
public class Echo {
    public static void main(String[] args) throws IOException {
        Process start = new ProcessBuilder("mysql").start();
        InputStream inputStream = start.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"gbk"));
        OutputStream outputStream = start.getOutputStream();
        BufferedOutputStream w=new BufferedOutputStream(outputStream);
        String s;

        Scanner scanner=new Scanner(System.in);
        while (true){
            while ((s=reader.readLine())!=null){
                System.out.println(s);
            }
            String s1 = scanner.nextLine();
            w.write(s1.getBytes());
        }

        // Process exec = Runtime.getRuntime().exec("D:\\java\\mysql使用软甲\\Navicat Premium 12\\navicat.exe");

    }

    private static void echo() throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out, true);
        String s;
        while ((s=reader.readLine())!=null && s.length()!=0){
            out.println(s);
        }
        out.close();
        reader.close();
    }
}
