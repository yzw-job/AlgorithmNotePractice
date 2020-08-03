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
        return get(root,key);// 从根节点出发 digui
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
        node.N=node.left.N+node.right.N+1;
        return node;
    }

    private boolean isEmpty(){
        return root.N==0;
    }

    public static void main(String[] args) {
        // construct symbol table <Sting , Integer>
        String[] arr={"b","m","h","d","f","e"};
        BinarySearchTree<String,Integer> bs=new BinarySearchTree();
        bs.put(arr[0],1);
//        bs.put(arr[1],2);
//        bs.put(arr[2],3);
//        bs.put(arr[3],4);
//        bs.put(arr[4],5);
//        bs.put(arr[5],6);

        System.out.println(bs.get("c"));
    }


}
