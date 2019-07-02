package thinkingJava.unit15;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/29 15:33
 * @Description:
 */
class Coffee{
    private static int count=0;
    private static final int id=count++;
    void getCoffee(){
        System.out.println("easy coffee");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+id;
    }
}
class Decorator extends Coffee{
    protected Coffee coffee;

    public Decorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void getCoffee() {
        coffee.getCoffee();
    }
}
class MikCoffee extends Decorator{

    public MikCoffee(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void getCoffee() {
        System.out.println("加牛奶");
        super.getCoffee();
    }
}
class SwifCoffee extends Decorator{

    public SwifCoffee(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void getCoffee() {
        System.out.println("加糖啦");
        super.getCoffee();
    }
}
public class CoffeeDecoration {
    public static void main(String[] args) {
        MikCoffee mikCoffee = new MikCoffee(new SwifCoffee(new Coffee()));
        mikCoffee.getCoffee();
    }
}
