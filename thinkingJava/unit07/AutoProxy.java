package thinkingJava.unit07;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/23 15:18
 * @Description: 使用Idea自动生成代理
 *                 ->alt+insert
 *                 ->Delegate Methods
 *                 ->选择属性
 *                 ->选择方法
 */
public class AutoProxy {
    private P p;

    public void fun1() {
        p.fun1();
    }

    public void fun2() {
        p.fun2();
    }
}
class P{
    void fun1(){}
    void fun2(){}
}
