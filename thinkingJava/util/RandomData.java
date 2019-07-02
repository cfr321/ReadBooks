package thinkingJava.util;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/29 12:42
 * @Description:
 */
public class RandomData {
    private static final char[] CHARS="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
    private static Random random=new Random(47);
    public static Generator<String> string(final int n){
        return  () -> {
            int len=n;
            StringBuilder builder=new StringBuilder();
            for (int i = 0; i < len; i++) {
                builder.append(CHARS[random.nextInt(CHARS.length)]);
            }
            return builder.toString();
        };
    }
    public static Generator<Integer> integer(){
        return ()->{
            return random.nextInt(10000);
        };
    }
    public static Generator<Boolean> byteGenerator(){
        return new Generator<Boolean>() {
            private boolean Value=false;
            @Override
            public Boolean next() {
                Value=!Value;
                return Value;
            }
        };
    }
    public static Generator<Character> characterGenerator(){
        return new Generator<Character>() {
            private int index=-1;
            @Override
            public Character next() {
                index=(index+1)%CHARS.length;
                return CHARS[index];
            }
        };
    }

    public static Generator<Double> doubleGenerator(){
        return ()->{
            long round = Math.round(random.nextDouble() * 100);
            return Double.valueOf(round/100);
        };
    }
    public static <T> T[] array(Class<T> type,Generator<T> generator,int size){
        T[] ts = (T[]) Array.newInstance(type, size);
        for (int i = 0; i < size; i++) {
            ts[i]=generator.next();
        }
        return ts;
    }
    public static <T> T[] fill(T[] ts,Generator<T> generator){
        for (int i = 0; i < ts.length; i++) {
            ts[i]=generator.next();
        }
        return ts;
    }
    public static void main(String[] args) {

    }
}