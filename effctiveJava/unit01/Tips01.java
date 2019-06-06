package effctiveJava.unit01;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 09:40
 * @Description: 用静态工厂方法替代构造器
 */
public class Tips01 {

    public static void main(String[] args) throws  Exception{
        //Files就提供了很多这样的方法，获取对象
        Files.newBufferedWriter(Paths.get(""));

        Integer[] o = (Integer[]) Array.newInstance(Integer.class, 5);

        BigInteger integer = BigInteger.valueOf(100);

    }

}
