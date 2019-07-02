package thinkingJava.unit07;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/23 15:49
 * @Description:
 */
public class Frog extends Amphibian{
    @Override
    protected void fun() {
        System.out.println("son unit05 fun");
    }

    public static void main(String[] args) {
        Amphibian frog=new Frog();
        frog.fun();

    }
}

class Amphibian{
    protected void fun(){
        System.out.println("mm unit05 fun");
    }
}