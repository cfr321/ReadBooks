package thinkingJava.unit18;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/7/2 11:31
 * @Description:
 */
public class WhileFast {
    private static abstract class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            try {
                long l = System.nanoTime();
                test();
                double d = System.nanoTime() - l;
                System.out.printf("%s : %.2f\n", name, d / 1.0e9);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        protected abstract void test() throws IOException;
    }

    private static int numOfInt = 4000000;
    private static Tester[] testers = {
            new Tester("Stream write") {
                @Override
                protected void test() throws IOException {
                    String name = "test.tmp";
                    OutputStream out = new FileOutputStream(name);
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(out));
                    for (int i = 0; i < numOfInt; i++) {
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },
            new Tester("map write") {
                @Override
                protected void test() throws IOException {
                    FileChannel rw = new RandomAccessFile("test.tmp", "rw").getChannel();
                    IntBuffer intBuffer = rw.map(FileChannel.MapMode.READ_WRITE, 0, rw.size()).asIntBuffer();
                    for (int i = 0; i < numOfInt; i++) {
                        intBuffer.put(i);
                    }
                    rw.close();
                }
            }
    };
    private static Tester[] testers2 = {
            new Tester("read  map") {
                @Override
                protected void test() throws IOException {
                    FileChannel rw = new FileOutputStream("test.tmp").getChannel();
                    IntBuffer intBuffer =
                            rw.map(FileChannel.MapMode.READ_WRITE, 0, rw.size()).asIntBuffer();
                    while (intBuffer.hasRemaining()) {
                        intBuffer.get();
                    }
                    rw.close();
                }
            }
    };

    private static void gzip() {
        System.out.println("begin to zip============");
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("test.tmp"));
             BufferedOutputStream out = new BufferedOutputStream
                     (new GZIPOutputStream(new FileOutputStream("test.gz")));) {
            int i;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
            System.out.println("zip  over=============");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("begin to read zip=============");
        try (BufferedReader reader =
                     new BufferedReader
                             (new InputStreamReader
                                     (new GZIPInputStream(new FileInputStream("test.gz"))))) {
            String s;
            while ((s=reader.readLine())!=null){
                System.out.println(s);
            }
            System.out.println("read over==============");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) throws FileNotFoundException {


        gzip();
    }
}
