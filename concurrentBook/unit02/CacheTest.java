package concurrentBook.unit02;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/7 14:10
 * @Description: 伪共享
 */
public class CacheTest {
    /**
     * 伪共享：我们的CPU读取主存中的数据是一块一块读的，一坨一坨读的，着就会读取其他变量，
     * 但读取的其他变量又被别的线程修改，这时候CPU就不得不再去读，因为一坨变了，就要重新读一坨。
     *
     * 1、对于单线程这样没毛病，可以一次就读多个变量过来，像数组，效率反倒更高
     * 2、对于多线程，别的线程改了我的这坨就要重新读取，这是会造成性能影响的。
     *
     * 解决：1.8前，用填充法，填充一些没用的，刚好填满一坨，这样就不会读取其他线程的变量。
     *       1.8以后用@sun.misc.Contended注解
     *
     */
    public static void main(String[] args) {
        long[][] arr=new long[1024][1024];
        Stopwatch timer=new Stopwatch();
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                arr[j][i]=i*2+j;    //0.008s
                arr[i][j]=i*2+j;    //0.004s
            }
        }
        System.out.println(timer.elapsedTime());
    }
}
