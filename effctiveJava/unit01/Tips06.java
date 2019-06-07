package effctiveJava.unit01;

import java.util.regex.Pattern;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/7 12:48
 * @Description:    避免不必要的对象创建。
 */
public class Tips06 {
    /**
     * 对象是应该创建还是创建一次就保存下来
     * 这很大程度却决于对象的创建的代价，如果是高代价的比如数据库连接池，可以保存下来
     *
     */
    private static final Pattern ROMAN=Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"+
                    "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"
    );
    static boolean isRomanNumeral(String s){
        return ROMAN.matcher(s).matches();
    }

    private static long sum(){
        //这里用Long会非常的慢，应该用long
        Long sum=0L;
        for (long i = 0; i <=Integer.MAX_VALUE; i++) {
            sum+=i;
        }
        return sum;
    }
    /**
     * 每个对象的创
     * 建都需要占用内存、需要GC，影响性能
     */
    public static void main(String[] args) {
        String a=new String("ma")+new String("in");//这里创建了5个对象 ma in分别在常量池和对中都有，
                                                    // 还有一个在堆里的main
        a.intern();                                 //这个时候发现常量池中有main,a依旧指向堆里面的main
        String b="main";                            //指向常量池中的main
        System.out.println(a==b);                   //所以false
        //System.out.println(isRomanNumeral("5651"));
        System.out.println(sum());
    }
}
