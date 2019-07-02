package thinkingJava.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/7/2 12:40
 * @Description:
 */
public class ZipUtil {
    public static final int byteSize = 128;
    private static final byte[] data=new byte[byteSize];

    public static void zip(String filePath, String outPath) {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outPath))) {
            System.out.println("begin to zip.....");
            File file = new File(filePath);
            if(file==null){
                throw new FileNotFoundException("没有这个文件");

            }
            compress(file, out, file.getName());
            System.out.println("over zip......");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void unZip(String zipFile, String outPath) {
        File outFile = new File(outPath);
        if (!outFile.exists()) {
            outFile.mkdirs();
        }
        if (!outFile.isDirectory()) {
            throw new IllegalArgumentException("解压路径需要是个文件夹");
        }
        try (ZipInputStream in = new ZipInputStream(new FileInputStream(zipFile))) {
            System.out.println("begin to unzip.......");
            unCompress(in, outFile);
            System.out.println("over unzip.......");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void compress(File file, ZipOutputStream out, String name) {
        BufferedOutputStream bufferOut = new BufferedOutputStream(out);
        try {
            if (file.isFile()) {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                out.putNextEntry(new ZipEntry(name));
                while (in.read(data) != -1) {
                    bufferOut.write(data, 0, byteSize);
                }
                in.close();
                bufferOut.flush();
            } else {
                File[] files = file.listFiles();
                if (files == null && files.length == 0) {
                    out.putNextEntry(new ZipEntry(file.getAbsolutePath() + "/"));
                    out.closeEntry();
                } else {
                    for (File file1 : files) {
                        compress(file1, out, name + "/" + file1.getName());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void unCompress(ZipInputStream in, File outFile) {
        BufferedInputStream bufferIn = new BufferedInputStream(in);
        try {
            ZipEntry entry;
            while ((entry = in.getNextEntry()) != null) {
                String filePath = outFile + "/" + entry.getName();
                File file = new File(filePath);
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                    while (bufferIn.read(data) != -1) {
                        out.write(data, 0, byteSize);
                    }
                    out.flush();
                    out.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        zip("F:\\Py","test.zip");
    }
}
