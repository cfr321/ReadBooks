package Algorithms.find.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/13 22:07
 * @Description: 一个Tree
 */
public class BTree<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    private static class Node<K, V> {
         K k;
        V v;
        Node left, right;
        int N;

        public Node(K k, V v, int n) {
            this.k = k;
            this.v = v;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node<Key, Value> root) {
        if (root == null) return 0;
        return root.N;
    }
    private Node getNode(Key key){
        Node node=root;
        while (node!=null){
            int i = key.compareTo((Key) node.k);
            if (i < 0) node = node.left;
            else if (i > 0) node = node.right;
            else return node;
        }
        return null;
    }
    public Value get(Key key) {
        return get(root, key);
    }

    public Value getRe(Key key) {
        Node node = root;
        while (node != null) {
            int i = key.compareTo((Key) node.k);
            if (i < 0) node = node.left;
            else if (i > 0) node = node.right;
            else return (Value) node.v;
        }
        return null;
    }

    private Value get(Node<Key, Value> root, Key key) {
        if (root == null) return null;
        Key k = root.k;
        int i = key.compareTo(k);
        if (i < 0) return (Value) get(root.left, key);
        else if (i > 0) return (Value) get(root.right, key);
        else return root.v;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node<Key, Value> put(Node<Key, Value> root, Key key, Value value) {
        if (root == null) return new Node<>(key, value, 1);
        int i = key.compareTo(root.k);
        if (i < 0) root.left = put(root.left, key, value);
        else if (i > 0) root.right = put(root.right, key, value);
        else root.v = value;
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public Key min() {
        if (root == null) return null;
        return min(root).k;
    }

    private Node<Key, Value> min(Node<Key, Value> root) {
        if (root.left == null) return root;
        return min(root.left);
//        while (root.left!=null){
//            root=root.left;
//        }
//        return root;
    }

    private Key max() {
        if (root == null) return null;
        return max(root).k;
    }

    private Node<Key, Value> max(Node<Key, Value> root) {
        if (root.right == null) return root;
        return min(root.right);
    }

    public Key floor(Key key) {
        Node node = root;

        while (node != null) {
            int i = key.compareTo((Key) node.k);
            if (i == 0) break;
            else if (i > 0) node = node.right;
            else node = node.left;
        }
        if (node != null) {
            return (Key) max(node.left).k;
        }
        return null;
    }

    public Key ceiling(Key key) {
        Node<Key, Value> node = ceiling(root, key);
        if (node == null) return null;
        return node.k;
    }

    private Node<Key, Value> ceiling(Node<Key, Value> root, Key key) {
        if (root == null) return null;
        int i = key.compareTo(root.k);

        if (i == 0) return root;
        else if (i > 0) return ceiling(root.right, key); //key>k,左边直接不要
        Node ceiling = ceiling(root.left, key);//有可能左边的都比key小，则返回null
        if (ceiling == null)
            return root;
        else return ceiling;
    }

    public Key select(int t) {
        Node<Key, Value> select = select(root, t);
        if (select == null) return null;
        return select.k;
    }

    private Node<Key, Value> select(Node<Key, Value> root, int t) {
        if (root == null) return null;
        int size = size(root.left);
        if (size == t) return root;
        else if (size < t) return select(root.right, t - size - 1); //去掉前一些
        else return select(root.left, t);
    }

    public int rank(Key key) {
        Node node = root;
        int size = 0;
        while (node != null) {
            int i = key.compareTo((Key) node.k);
            if (i == 0) {
                size += size(node.left);
                return size;
            } else if (i > 0) {
                size = size + 1 + size(node.left);
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return 0;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key min, Key max) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, min, max);
        return queue;
    }

    private void keys(Node<Key, Value> root, Queue<Key> queue, Key min, Key max) {
        if (root == null) return;
        int i = min.compareTo(root.k);
        int j = max.compareTo(root.k);

        if (i < 0) keys(root.left, queue, min, max);
        if (i <= 0 && j >= 0) queue.add(root.k);
        if (j > 0) keys(root.right, queue, min, max);
    }

    public void deleteMin() {
        root = deleteMin(root);

    }

    private Node<Key, Value> deleteMin(Node<Key, Value> root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node<Key, Value> deleteMax(Node<Key, Value> root) {
        if (root.right == null) return root.left;
        root.right = deleteMax(root.right);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    private Node<Key, Value> delete(Node<Key, Value> root, Key key) {
        if (root == null) return null;
        int i = key.compareTo(root.k);
        if (i > 0) root.right = delete(root.right, key);
        else if (i < 0) root.left = delete(root.left, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            Node t = root;
            root = min(root.right);
            root.right = deleteMin(t.right); //这里不能和下一行调换，root其实是接在t.right下面的
            root.left = t.left;              //如果改变顺序，会使得这个min删掉的不是t.right里面最小的，造成循环引用
            /*
            如果改变顺序：
                     3 (t)            3 (t)
                   /  \             /  \
                  1    5   ==>    1     5  ==>  deleteMin(t.right)删掉的只是 1，
                     /                 /         然后root.right指向5 -> 4(root）循环引用产生
                    4(root)          4（root)
                                    /
                                  1（被删）
             */

        }
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public void delete2(Key key) {
        Node node = root;
        Node pre = root;
        while (node != null) {
            int i = key.compareTo((Key) node.k);
            if (i > 0) {
                pre = node;
                node = node.right;
            } else if (i < 0) {
                pre = node;
                node = node.left;
            } else {
                if (node.left == null) node = node.right;
                else if (node.right == null) node = node.left;
                else {
                    Node t = node;
                    node = min(node.right);
                    node.right = deleteMin(t.right);
                    node.left = t.left;
                }
                int p = key.compareTo((Key) pre.k);
                if (p > 0) {
                    pre.right = node;
                } else if (p < 0) {
                    pre.left = node;
                } else {
                    root = node;
                }
                return;
            }
        }
    }

    /**
     * 直观的打印树
     */
    public void printTree() {
        System.out.println("Tree:");
        printInOder(root, 0, "H", 15);
        System.out.println();
    }

    private void printInOder(Node<Key, Value> root, int h, String str, int l) {
        if (root == null) return;
        printInOder(root.right, h + 1, "v", l);

        String val = str + root.k.toString() + "/" + root.v + str;
        int lenM = val.length();
        int lenL = (l - lenM) / 2;
        String s = getSpace(lenL);
        System.out.println(getSpace(h * l) + s + val + s);
        printInOder(root.left, h + 1, "^", l);
    }

    private String getSpace(int lenL) {
        StringBuilder re = new StringBuilder("");
        String s = " ";
        for (int i = 0; i < lenL; i++) {
            re.append(s);
        }
        return re.toString();
    }

    public void pout() {
        Node node = root;
        pout(node);
    }

    private void pout(Node node) {
        if (node == null) return;
        System.out.println(node.k + " " + node.v);
        pout(node.left);
        pout(node.right);
    }

    public String serial() {
        Node node = root;
        return serial(node);
    }

    private String serial(Node node) {
        if (node == null) return "#=";
        StringBuilder builder = new StringBuilder();
        builder.append(node.k).append("/").append(node.v).append("=");
        builder.append(serial(node.left));
        builder.append(serial(node.right));
        return builder.toString();
    }
    public boolean isBT(){
        return root.N==bTSize(root);
    }

    private int bTSize(Node<Key,Value> root) {
        if(root==null) return 0;
        int n=0;
        n=bTSize(root.right)+bTSize(root.left)+1;
        return n;
    }

    public static void main(String[] args) {
        BTree<Integer, String> bTree = new BTree<>();
        bTree.put(3, "aaa");
        bTree.put(1, "bbb");
        bTree.put(2, "ccc");
        bTree.put(4, "aaa");
        //bTree.delete(3);
        bTree.delete2(1);
        bTree.printTree();
    }
}
