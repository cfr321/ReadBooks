package thinkingJava.unit12;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/25 20:15
 * @Description:
 */
public class LoggingException {
}

class MyException extends Exception{
    private static Logger logger=Logger.getLogger("e");

    public MyException() {
        StringWriter writer=new StringWriter();
        printStackTrace(new PrintWriter(writer));
        logger.severe(writer.toString());
    }
}