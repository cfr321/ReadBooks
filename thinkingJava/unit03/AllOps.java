package thinkingJava.unit03;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/17 23:16
 * @Description:
 */
public class AllOps {
    public AllOps() {
        System.out.println("aa");
        new AllOps();
    }

    void boolTest(boolean x, boolean y){
       // x=~y;
    }
    void  charTest(char x,char y){
        x= (char) (x*y);
        x= (char) (x+y);
        x++;
        y--;
        y*=x;
        x= (char) -y;
    }
    void doubleTest(double x,double y){
        //! x>>=1;
        //! x=x^y;
    }

    static void printInt(int x){

        for (int i = 0; i < 32; i++) {
            System.out.print(x&1);
            x<<=1;
        }
    }
    static void binaryPrint (int q) {
        if(q == 0) System.out.print(0);
        else {
            int nlz = Integer.numberOfLeadingZeros(q);
            q <<= nlz;
            for(int p = 0; p < 32 - nlz; p++) {
                int n = (Integer.numberOfLeadingZeros(q) == 0) ? 1 : 0;
                System.out.print(n);
                q <<= 1;
            }
        }
        System.out.println("");
    }
    public static void main(String[] args) {

    }
}
