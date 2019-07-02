package thinkingJava.util;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/28 16:17
 * @Description:
 */
public class Stack<T> {
    private static class Node<T>{
        T item;
        Node<T> next;

        public Node() {
            item=null;
            next=null;
        }
        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
        boolean end(){
            return item==null && next==null;
        }
    }
    private Node<T> top=new Node<>();

    public void push(T t){
        top=new Node<>(t,top);
    }
    public T pop(){
        T item = top.item;
        if(!top.end()){
            top=top.next;
        }
        return item;
    }
    public T peek(){
        return top.item;
    }
}
