package effctiveJava.unit01;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/7 12:35
 * @Description:    优先考虑 依赖注入 来引入资源
 */
public class Tips05 {
    /**
     * 对于有对其他类依赖的类，我们不应该用单例或者静态工具来对象实例
     * 也不应该在类里面去自己创建这些依赖
     * 应该用依赖注入的方式，当要使用的时候来选择性的注入这些依赖，常见的注入方式是构造器和set注入
     *
     * 但是在复杂的项目中，这种依赖的注入是复杂的，所以需要依赖注入框架 Spring
     *
     *  这就提高的类的灵活性、可重用性、可测性，而且通常应该去依赖接口，注入接口的实现。（策略模式）
     */
    private Tips04 tips04;

    public Tips05(Tips04 tips04) {
        this.tips04 = tips04;
    }
}
