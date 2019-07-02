package thinkingJava.unit13;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/26 19:05
 * @Description:
 */
public class Hex {
    private static String format(byte[] bytes){
        StringBuilder builder=new StringBuilder();
        int n=0;
        for (byte aByte : bytes) {
            if(n%16==0)
                builder.append(String.format("%05X ",n));
            builder.append(String.format("%02X ",aByte));
            n++;
            if(n%16==0) builder.append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }
    private static byte[] read(String path) throws Exception {

        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        URL resource1 = Hex.class.getClassLoader().getResource(path);
        FileInputStream inputStream=new FileInputStream(resource.getPath());


        FileChannel channel = inputStream.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate((int) channel.size());
        channel.read(allocate);
        inputStream.close();
        channel.close();
        return allocate.array();
    }

    private static byte[] read2(String file) throws IOException, URISyntaxException {
        URL resource = Hex.class.getClassLoader().getResource(file);
        URI uri = resource.toURI();
        return Files.readAllBytes(Paths.get(uri));
    }
    public static void main(String[] args) throws Exception {
        byte[] read = read("thinkingJava/unit13/Hex.java");
        byte[] bytes = read2("test.properties");
        String format = format(read);
        String format1 = format(bytes);
        System.out.println(format);
        System.out.println("=======================");
        System.out.println(format1);

    }
}
