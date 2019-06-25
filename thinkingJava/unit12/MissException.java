package thinkingJava.unit12;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/25 21:40
 * @Description:
 */
public class MissException {

    public static void main(String[] args) {
        try {
            try{
                throw  new Exception("try 的 Exception");
            }finally {
                throw new Exception("finally 的 E");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
