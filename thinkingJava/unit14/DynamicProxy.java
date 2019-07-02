package thinkingJava.unit14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/27 23:55
 * @Description:
 */
interface Inter {
    void doSomeThing();
}

class Real implements Inter {

    @Override
    public void doSomeThing() {
        System.out.println("Real do some thing");
    }

    public static void main(String[] args) {
        Inter real = new Inter() {
            @Override
            public void doSomeThing() {

            }
        };
        System.out.println(real instanceof Real);
    }
}

public class DynamicProxy<T> implements InvocationHandler {

    private T t;
    private Handler handler;

    public DynamicProxy() {
    }

    public DynamicProxy(T t) {
        this.t = t;
    }

    public DynamicProxy(Handler handler) {
        this.handler = handler;
    }

    public DynamicProxy(T object, Handler handler) {
        this.handler = handler;
        this.t = object;
    }

    public T getProxy() {
        return getProxy(t);
    }

    public T getProxy(T t) {
        T t1 = (T) Proxy.newProxyInstance(
                t.getClass().getClassLoader(),
                t.getClass().getInterfaces(), this);
        return t1;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = null;
        if (handler != null) {
            try {
                handler.before();
                invoke = method.invoke(t, args);
                handler.after();
            } catch (Exception e) {
                handler.exc();
            }
        } else {
            invoke = method.invoke(t, args);
        }
        return invoke;
    }

    public static void main(String[] args) {
        DynamicProxy<Inter> dynamicProxy = new DynamicProxy<Inter>(new Real(), new Handler() {
            @Override
            public void before() {
                System.out.println("before");
            }

            @Override
            public void after() {
                System.out.println("after");
            }

            @Override
            public void exc() {
                System.out.println("ex");
            }
        });
        Inter proxy = dynamicProxy.getProxy();
        proxy.doSomeThing();
    }
}

