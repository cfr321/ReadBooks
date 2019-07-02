package thinkingJava.unit18;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/7/1 22:43
 * @Description:
 */
public class TransferTo {
    public static void main(String[] args) throws Exception {
        MappedByteBuffer rw = new RandomAccessFile("", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 0x8fffffff);



    }

    private static void some() throws IOException {
        File file=new File("H:\\java\\jdk12\\ha_Xshell_25206.zip");
        File file1 = new File("H:\\java\\jdk12\\test\\out.zip");
        if(!file1.exists()){
            file1.createNewFile();
        }
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.asCharBuffer().append("nihao0");
        allocate.position();
        //   allocate.reset()
        allocate.get();
        FileChannel out=new FileOutputStream(file1).getChannel(),
                    in=new FileInputStream(file).getChannel();
        //out.transferFrom(in,0,in.size());
        in.transferTo(0,in.size(),out);
        in.close();
        out.close();
    }
}
