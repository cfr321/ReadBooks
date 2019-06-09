package effctiveJava.unit01;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/8 15:22
 * @Description:    消除不必要的对象
 */
public class Tips07 implements AutoCloseable{
    /**
     * 对于类是自己管理内存的，我们需要注意内存泄漏
     *
     * 比如栈stack，当弹出一个时，我们应该样弹出对象的引用为空
     */

    public static void main(String[] args) {

        new LinkedHashMap(3,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return super.removeEldestEntry(eldest);
            }
        };
    }

    @Override
    public void close() throws Exception {

    }
}
