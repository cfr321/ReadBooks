package thinkingJava.unit18;

import java.io.*;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/30 16:21
 * @Description:
 */
public class DirList {
    public static void main(String[] args) {
        File file = new File("src\\main\\java\\thinkingJava\\unit05");
        String[] list = file.list(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(".*[C].*\\.java");

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        long length = file.length();



        boolean b1 = file.canRead();
        boolean b = file.canWrite();
        URI uri = file.toURI();
        //  file.renameTo(new File("unit05"));

        try {
            FileChannel file1 = new FileOutputStream("file").getChannel();
            new StringWriter();
            new StringReader("string");
            PipedInputStream pipedInputStream = new PipedInputStream();
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            pipedInputStream.connect(pipedOutputStream);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

        System.out.println(Arrays.toString(list));
    }
}
