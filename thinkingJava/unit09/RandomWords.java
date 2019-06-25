package thinkingJava.unit09;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/24 11:30
 * @Description:
 */
public class RandomWords implements Readable {

    private static Random random=new Random(47);
    private static final char[] capitals="QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
    private static final char[] lowers="qwertyuiopasdfghjklzxcvbnm".toCharArray();
    private int count;
    private int length;

    private void setLength(int length) {
        this.length = length;
    }

    public RandomWords(int count,int length) {
        this.count = count;
        this.length=length;
    }

    public RandomWords(int length) {
        this(1,length);
    }

    @Override
    public int read(@NotNull CharBuffer cb) throws IOException {
        if(count--==0 || length<=0)
            return -1;
        cb.append(capitals[random.nextInt(capitals.length)]);

        for (int i = 0; i < length-1; i++) {
            cb.append(lowers[random.nextInt(lowers.length)]);
        }
        cb.append(" ");
        return length;
    }
}
interface Moths{
    int ES=10;  //is static and final
}