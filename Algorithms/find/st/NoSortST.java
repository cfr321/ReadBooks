package Algorithms.find.st;

import java.util.Iterator;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/12 19:43
 * @Description:
 */
public class NoSortST<Key, Value> {


     private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node fist;
    private int size;

    public Value get(Key o) {
        for (Node x = fist; x != null; x = x.next) {
            if (x.key.equals(o))
                return x.value;
        }
        return null;
    }


    public void put(Key o, Value o2) {
        for (Node x = fist; x != null; x = x.next)
            if (x.key.equals(o)) {
                x.value = o2;
                return;
            }
        fist = new Node(o, o2, fist);
        this.size++;
    }

    public int size(){
        return size;
    }

    public Value delete(Key o){
        if(fist.key.equals(o)){
            Value value = fist.value;
            fist=null;
            this.size--;
            return value;
        }
        Node pre=fist;
        Node node=pre.next;
        for (;node!=null;pre=node,node=node.next){
            if(node.key.equals(o)){
                Value value = node.value;
                pre.next=node.next;
                node=null;
                this.size--;
                return value;
            }
        }
        return null;
    }

    public Iterator<Key> keys() {
        Iterator<Key> objectIterator = new Iterator<Key>() {
            Node x = fist;

            @Override
            public boolean hasNext() {
                return x == null;
            }

            @Override
            public Key next() {
                Key key = x.key;
                x = x.next;
                return key;
            }
        };
        return objectIterator;
    }

    public static void main(String[] args) {
        NoSortST<Integer,String> st=new NoSortST<>();
        st.put(1,"aaa");
        st.put(2,"bbb");
        st.delete(1);


        String s = st.get(2);
        System.out.println(s);
        System.out.println(st.size());
    }
}
