package Algorithms.find.bt;

import java.util.NoSuchElementException;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/14 16:38
 * @Description:
 */
/*
    1、红的只能在左子树
    2、不能连续出现两次红
    3、根总是黑，叶子到根黑的数量要一致
 */
public class RBTree<K extends Comparable<K>, V> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    private Node root;

    private static class Node<K, V> {
        K k;
        V v;
        Node left, right;
        boolean color;
        int N;

        Node(K k, V v, boolean color, int n) {
            this.k = k;
            this.v = v;
            this.color = color;
            N = n;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) return false;

        return node.color;
    }

    public int size() {
        return size(root);
    }
    public boolean isEmpty() {
        return root == null;
    }

    private int size(Node<K, V> root) {
        if (root == null) return 0;

        return root.N;
    }

    private Node roLeft(Node h) {
        Node node = h.right;
        h.right = node.left;
        node.color = h.color;
        h.color = RED;
        node.left = h;
        node.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return node;
    }

    private Node roRight(Node h) {
        Node node = h.left;
        h.left = node.right;
        node.right = h;
        node.color = h.color;
        h.color = RED;
        node.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return node;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    public void put(K k, V v) {
        root = put(root, k, v);
        root.color = BLACK;
    }

    private Node put(Node node, K k, V v) {
        if (node == null) return new Node(k, v, RED, 1);
        int i = k.compareTo((K) node.k);
        if (i < 0) node.left = put(node.left, k, v);
        else if (i > 0) node.right = put(node.right, k, v);
        else node.v = v;

        if (isRed(node.right) && !isRed(node.left))
            node = roLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = roRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        node.N = size(node.right) + size(node.left) + 1;
        return node;
    }

    public void deleteMin(){
        if (isEmpty()) throw new NoSuchElementException("root is empty");
        if(!isRed(root.right) && !isRed(root.left)){
            root.color=RED;
        }
        root=deleteMin(root);
        if(!isEmpty())
            root.color=BLACK;
    }

    private Node deleteMin(Node node) {
        //没左必定没右
        if(node.left==null)
            return null;
        if(!isRed(node.left) && !isRed(node.left.left)){
            node=moveRedLeft(node);
        }
        node.left=deleteMin(node.left);
        return balance(node);
    }

    private Node balance(Node node) {
        if(isRed(node.right)) node=roLeft(node);
        if(isRed(node.right) && !isRed(node.left)) node=roLeft(node);
        if(isRed(node.left) && isRed(node.left.left)) node=roRight(node);
        if(isRed(node.left) && isRed(node.right)) flipColors(node);
        node.N=size(node.left)+size(node.right)+1;
        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if(isRed(node.left.right)){
            node.right=roRight(node.right);
            node=roLeft(node.left);
        }
        return node;
    }

    public void deleteMax(){
        if (isEmpty()) throw new NoSuchElementException("root is empty");
        if(!isRed(root.right) && !isRed(root.left)){
            root.color=RED;
        }
        root=deleteMax(root);
        if(!isEmpty())
            root.color=BLACK;
    }

    private Node deleteMax(Node node) {
        //把左的变成右的
        if(isRed(node.left))
            node=roRight(node);
        //那么没右必然没左
        if(node.right==null)
            return null;
        //左边没有红
        if(!isRed(node.right) && !isRed(node.right.left)){ //node.right.left是即将旋转过来的
            node=moveRedRight(node);
        }
        node.right=deleteMax(node.right);
        return balance(node);
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if(isRed(node.left.left)){
            node=roRight(node);
        }
        return node;
    }

    public void delete(K k){
        if (isEmpty()) throw new NoSuchElementException("root is empty");
        if(!isRed(root.right) && !isRed(root.left)){
            root.color=RED;
        }
        root=delete(root,k);
        if(!isEmpty())
            root.color=BLACK;
    }

    private Node delete(Node node,K k) {
        int i = k.compareTo((K) node.k);
        if(i<0){
            if (!isRed(node.left) && !isRed(node.left.left))
                node=moveRedLeft(node);
            node.left=delete(node.left,k);
        }else {
            if(isRed(node.left))
                roRight(node);
            if(i==0 && node.right==null)
                return null;
            if(!isRed(node.right) && !isRed(node.right.left))
                node=moveRedRight(node);
            if(i==0){
                Node min=getMin(node.right); //thr node.right not null
                node.k=min.k;
                node.v=min.v;
                node.right=deleteMin(node.right);
            }else {
                node.right=delete(node.right,k);
            }
        }
        return balance(node);
    }

    private Node getMin(Node right) {
        while (right.left!=null){
            right=right.left;
        }
        return right;
    }

    public V get(K k){
        Node node=root;
        while (node!=null){
            int i = k.compareTo((K) node.k);
            if(i==0) return (V) node.v;
            else if(i>0) node=node.right;
            else node=node.left;
        }
        return null;
    }

    public K max(){
        Node node=root;
        while (node.right!=null){
            node=node.right;
        }
        return (K) node.k;
    }

    public K min(){
        Node node=root;
        while (node.left!=null){
            node=node.left;
        }
        return (K) node.k;
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
}
