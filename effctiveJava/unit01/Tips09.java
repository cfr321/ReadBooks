package effctiveJava.unit01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/8 18:28
 * @Description:    try-with-resources优先于try-finally
 */
public class Tips09 {
    //一般来说我们用finally来关闭服资源
    static String firstLineOfFile(String path) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
        try {
            return bufferedReader.readLine();
        }finally {
            bufferedReader.close();
        }
      }
    //当情况变得复杂，代码也就变得一团糟
    static void copy(String src,String dst) throws Exception {
        InputStream in=new FileInputStream(src);
        try {
            OutputStream out=new FileOutputStream(dst);
            try {
                int n;
                while ((n=in.read())!=-1){
                    out.write(n);
                }
            }finally {
                out.close();
            }
        }finally {
            in.close();
        }
    }

    static void copy2(String src,String dst) throws Exception {

        try ( OutputStream out=new FileOutputStream(dst);
              InputStream in=new FileInputStream(src)) {
            int n;
            while ((n=in.read())!=-1){
                out.write(n);
            }
        }
    }
}
