package ChapterThree;

import kotlin.reflect.jvm.internal.ReflectProperties;

import java.security.Key;

/**
 * 二叉查找树：将链表插入的灵活性与有序数组查找的高效性结合起来
 *
 */
public class BinarySearchTree <Key extends Comparable<Key>, Value>{
    private Node root; // 根节点

    // 定义节点
    private class Node{
        private Key key;         // 键
        private Value value;      // 值
        private Node left,right; // 左右子树链接
        private int N;           // 以该节点为根的子树中的节点总数

        public Node(Key key,Value value,int N){
            this.key=key;
            this.value=value;
            this.N=N;
        }
    }


    // 节点的数量
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if(x==null)return 0;
        else return x.N;
    }


    // 查找某节点对应的值
    public Value get(Key key){
        return get(root,key);// 从根节点出发 递归
    }
    private Value get(Node node,Key key){
        if(node==null)return null;
        int cmp=key.compareTo(node.key);
        if (cmp<0)return get(node.left,key);
        else if(cmp>0)return get(node.right,key);
        else return node.value;
    }

    //
    public void put(Key key,Value value){
        // 查找key ,找到替换。没有新增
        // 每次从root 根节点查找
        root=put(root,key,value);
    }
    private Node put(Node node,Key key,Value value){
        // node 为找到的地方 为空则新建
        if(node==null)return new Node(key,value,1);
        int cmp=key.compareTo(node.key);
        //
        if(cmp<0)node.left=put(node.left,key,value);
        else if(cmp>0)node.right=put(node.right,key,value);
        else node.value=value; // 找到
//        node.N=node.left.N+node.right.N+1; // node.left可能会空引用，没有初始化N变量
        node.N=size(node.left)+size(node.right)+1;
        return node;
    }

    // 查找最小的键值 依次查找左子节点
    public Key min(){
        return min(root);
    }
    private Key min(Node x){
        if(x==null)return null;
        if (x.left!=null){// 父节点的左边子节点存在，向下找
            return min(x.left);
        }
        return x.key;
    }

    // 查找最大的键值 依次查找右子节点
    public Key max(){
        return max(root);
    }
    private Key max(Node x) {
        if(x==null)return null; // 判断父节点是否为空
        if(x.right!=null)return max(x.right);
        return x.key;
    }

    // 大于节点值向节点右子节点边找，（右边的值大）
    // 小于节点值向节点左边找，（左边值比较小），左边找到头没了，则该父节点就是。

    public Key ceiling(Key key){
        Node x=ceiling(root,key);
        if(x==null)return null;
        else return x.key;
    }
    private Node ceiling(Node x, Key key) {
        if(x==null)return null;
        int cmp=key.compareTo(x.key);
        if(cmp==0)return x;         //找到等于
        if(cmp<0){ // key 小于该节点，向左边找更接近的值，左边如果没找到，则该父节点就是。
            Node temp=ceiling(x.left,key);
            if(temp==null)return x;
            else return temp;
        }

        // key 大 向右找
        return ceiling(x.right,key);

    }


    public Key floor(Key key){
        Node x=floor(root,key);
        if(x==null)return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if(x==null)return null;
        int cmp= key.compareTo(x.key);
        if (cmp==0)return x;
        if(cmp<0)return floor(x.left,key);

        // cmp>0
        Node t=floor(x.right,key);
        if(t!=null)return t;
        else return x;
    }


    public int rank(Key key){
        return rank(root,key);
    }
    // 小于key 的个数。key 左边全部小于key
    private int rank(Node x,Key key){
        if(x==null)return 0;
        int cmp= key.compareTo(x.key);
        if(cmp<0)return rank(x.left,key);   //节点值大于key，继续找直到节点值小于key,那么节点值左边的都小于key
        else if(cmp>0)return size(x.left)+rank(x.right,key)+1;// 找到该节点，因为不等于，左边的加上本身，以及右边找到的
        else return size(x.left);// 等于
    }

    public void deleteMin(){
        root = deleteMin(root);
    }
    // 一直向左边找，直到空，并且把父节点指向它的有节点即可
    private Node deleteMin(Node x){
        if(x.left==null) return x.right;//记录右子节点
        x.left = deleteMin(x.left);//一直相左找 x.left 指向下一个
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static void main(String[] args) {
        // construct symbol table <String , String>

        BinarySearchTree<Double,String> bt=new BinarySearchTree();
        bt.put(1.0,"b is b");
        bt.put(28.0,"b is b");
        bt.put(34.0,"b is b");
        bt.put(40.0,"b is b");
        bt.put(50.0,"b is b");
        bt.put(69.0,"b is b");
        bt.put(71.0,"b is b");
        bt.put(88.8,"b is b");
        bt.put(99.4,"b is b");

//        System.out.println(bt.get(11.0));
//        System.out.println(bt.min());
//        System.out.println(bt.max());
//        System.out.println(bt.ceiling("ab"));
//        System.out.println(bt.floor("ab"));
//        System.out.println(bt.ceiling(19.5));
        System.out.println(bt.rank(58.0));
        bt.deleteMin();
        System.out.println(bt.min());
    }


}
