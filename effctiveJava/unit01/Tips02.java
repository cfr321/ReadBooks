package effctiveJava.unit01;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/6 09:52
 * @Description: 遇到多个构造器参数时要考虑使用  构建器
 */
public class Tips02 {
    /**
     * 一个类有多个属性需要设置
     *
     * 1、我们可以重叠构造器，可行但是，缺点代码混乱、可读性差（安全不可读）
     * 2、我们用JavaBeans模式，通过set设置属性（可读不安全）
     *    缺点：set过程是一步一步的，过程中对象属性不一致，没保证不变性，另外这个过程线程也不是安全的。
     *
     * 3、构建者模式：内置一个Builder，通过它进行对象的构建，下面是个事例
     */

    //设为final认为都是需要设置的值
    private final int size;
    private final int tal;
    private final int name;
    private Tips02(Builder builder){
        this.size=builder.size;
        this.tal=builder.tal;
        this.name=builder.name;
    }

    public static class Builder{
        //客户必须提供的值
        private final int size;
        //可以选择默认的值
        private int tal=0;
        private int name=0;

        public Builder(int size) { this.size = size; }
        public Builder tal(int tal){ this.tal=tal;return this; }
        public Builder name(int name){ this.name=name;return this; }

        //构建真正的实体类
        public Tips02 build(){ return new Tips02(this); }
    }

    //test
    public static void main(String[] args){
        Tips02 build = new Builder(10).tal(20).name(30).build();
    }
}
