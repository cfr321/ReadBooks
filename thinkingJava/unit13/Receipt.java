package thinkingJava.unit13;

import java.util.Formatter;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/26 18:20
 * @Description:
 */
public class Receipt {
    private Formatter formatter=new Formatter(System.out);
    public static final String FORMAT="%-15s %5s %10s\n";
    public void printTitle(){
        formatter.format(FORMAT,"Item","Qty","Price");
        formatter.format(FORMAT,"----","---","-----");
    }
    private void print(){}
    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        System.out.println(Integer.toOctalString(16));

    }
}
