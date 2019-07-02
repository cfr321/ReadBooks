package thinkingJava.unit18;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/7/2 18:22
 * @Description:
 */
class Car{
    public void f(){
        System.out.println("能跑");
    }
}
public class CarDecorator extends Car{
    private Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void f() {
        car.f();
    }

    public static void main(String[] args) {
        FastCar fastCar = new FastCar(new FlyCar(new Car()));
        fastCar.f();
    }
}
class FastCar extends CarDecorator{

    public FastCar(Car car) {
        super(car);
    }

    @Override
    public void f() {
        System.out.println("跑的贼快的car");
        super.f();
    }
}
class FlyCar extends CarDecorator{

    public FlyCar(Car car) {
        super(car);
    }

    @Override
    public void f() {
        System.out.println("能飞得car");
        super.f();

    }
}