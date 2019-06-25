package thinkingJava.unit12;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/25 20:43
 * @Description:
 */
public class Rethrowing {

    private static void f() throws Exception {
        throw new Exception("oringin e from f()");
    }

    private static void g() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("g()  get  ex");
            e.printStackTrace(System.out);
            throw e;
        }
    }

    private static void h() throws Exception {
        try {
            f();
        }catch (Exception e){
            System.out.println("h()  get  ex");
            e.printStackTrace(System.out);
            throw (Exception) e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            g();
        } catch (Exception e) {
            System.out.println("main1================");
            e.printStackTrace(System.out);
        }

        System.out.println("\n-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        try {
            h();
        }catch (Exception e){
            System.out.println("main2=================");
            e.printStackTrace(System.out);
        }

        NullPointerException exception = new NullPointerException();
        MyException myException=new MyException();
        myException.initCause(exception);
        try {
            throw myException;
        } catch (MyException e) {
            e.getCause();
        }
    }
}
