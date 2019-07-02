package thinkingJava.unit18;

import java.io.*;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/7/2 16:59
 * @Description:
 */
class Data implements Serializable{
    private  String name;
    private int date;

    public Data(String name, int date) {
        this.name = name;
        this.date = date;
    }
    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
class Ser1 implements Externalizable{
    private  int data;

    public Ser1(int data) {
        this.data = data;
        System.out.println("do constructor");
    }

    public Ser1() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("do write");
        out.writeInt(data);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("do read");
        data=in.readInt();
    }

    @Override
    public String toString() {
        return "Ser1{" +
                "data=" + data +
                '}';
    }
}
public class ObjectSerializable implements Serializable {
    private Data data;

    public ObjectSerializable(Data data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "ObjectSerializable{" +
                "data=" + data +
                '}';
    }
    private static <T> void ser(T object,String outfile){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outfile));
            out.writeObject(object);
            out.close();
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(outfile));
            T object1 = (T) in.readObject();
            System.out.println(object1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        Ser1 ser1 = new Ser1(1);
        ser(ser1,"test1.out");
    }
}
