package thinkingJava.unit05;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/21 15:07
 * @Description:
 */
public class PrimitiveOverloading {
//    void f1(char a){
//        System.out.println("char"+a);
//    }
    void f1(int a){
        System.out.println("int"+a);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        PrimitiveOverloading primitiveOverloading = new PrimitiveOverloading();
        primitiveOverloading.f1('1');
        Class<String> aClass = String.class;
    }
}
