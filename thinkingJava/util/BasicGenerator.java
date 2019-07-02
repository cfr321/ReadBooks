package thinkingJava.util;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 17:44
 * @Description: T只能是有无参构造器的
 */
public class BasicGenerator<T> implements Generator<T> {

    private Class<T> type;

    public BasicGenerator(Class type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static <T> Generator<T> create(Class type){
        return new BasicGenerator<>(type);
    }
}
