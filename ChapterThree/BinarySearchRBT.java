package ChapterThree;

import kotlin.reflect.jvm.internal.ReflectProperties;

import java.security.Key;

/**
 * 2020 8/7 9：40 今天也是努力的一天啊 ^ ^
 * 红黑二叉树实现的 2-3 查找树
 * 基本思想：红链接将连个2-节点连接，构成一个3-节点
 * 满足的条件（等价定义）：黑链接均为左连接;没有任何一个节点同时连接两个红链接；
 * 该树是完美（黑色）平衡的，即任意空连接到根节点的黑色节点数量相等-->
 * -->红链接连接的两个节点对于黑色节点来说等同于一个节点
 *
 * 优点：即可用二叉树中简洁的查找方法，也可利用2-3树中高效平衡的插入的方法
 *
 */
public class BinarySearchRBT<Key extends Comparable<Key>,Value> {

    // 创建根节点
    private Node root;

    // 每个节点都有一个指向自己的连接，指向它的为红色则true；否则false
    private static final boolean RED =true;
    private static final boolean BLACK =false;

    private class Node{
        Key key;    // 键
        Value value; // 值
        Node left,right;// 左右节点
        int N;
        boolean color; // 颜色

        // 构造函数
        Node(Key key, Value value,int N,boolean color){
            this.key=key;
            this.value=value;
            this.N=N;
            this.color=color;
        }

    }

    // 判断该节点是否为红色节点
    private boolean isRed(Node x){
        if(x==null)return false;//空连接必须为黑色
        return x.color;
    }

    //左旋右旋 父节点指向二建三叉树的哪个键，原来指向左边的指向右边（左边旋下去）
    Node rotLeft(Node zuo){
        Node x=zuo.right; // x指向zuo的右节点
        zuo.right=x.left; // ZUO的有节点为原来zuo的右子节点的左子节点
        x.left=zuo;
        x.color=zuo.color;
        zuo.color=RED;
        x.N=zuo.N;
        zuo.N=size(zuo.left)+size(x.left)+1;
        return x;  // 互换左右

    }

    //原来指向右边的指向左边 （右边选下去）
    Node rotRight(Node h){
        Node x=h.left;
        h.left=x.right;
        x.right=h;
        x.color=h.color;
        h.color=RED;
        x.N=h.N;
        h.N=1+size(h.left)+size(h.right);
        return x;  // 互换左 右
    }


    private int size(Node x) {
        return x.N;
    }

    /**
     * 插入：
     * 一、单个2-节点： 直接插入红链接，key大于root，右旋一下
     * 二、单个3-节点： 三种情况 新的键值在 两个键的左右，以及中间
     * （produce a node with two red lines which need to be modified）
     * 1、新键大于两个节点，直接把中间的节点提上去（p279）,两个红链接转为两个黑链接
     *
     * 三、向树的底部3-节点加入一个新的节点
     * 操作步骤：要在一个3-节点中插入，先创建一个临时的4-节点，分解并将红链接由中间键值
     *          传到父节点，依次向上递归，直到遇到2-节点，向上加1
     */

    // 颜色转换 将一个节点的两个红子节点变黑，并且将父节点的颜色由黑变红
    private void flipColor(Node h){// h为父节点
        h.color=RED;
        h.left.color=BLACK;
        h.right.color=BLACK;
    }

    // 三叉树的添加情况三种 （P288）
    private Node put(Node h, Key key, Value value){
        // 当前键值为空 则新建
        if(h==null)return new Node(key,value,1, RED);
        int cmp=key.compareTo(h.key);
        if(cmp<0) h.left = put(h.left, key, value);
        else if(cmp>0)h.right=put(h.right,key,value);
        else h.value=h.value;

        // 三种情况旋转
        // hei节点右链接为红
        if(isRed(h.right)&&!isRed(h.left)) h=rotLeft(h);
        //连续两个红左连接
        if(isRed(h.left)&&isRed(h.left.left)) h=rotRight(h);
        //颜色转换
        if(isRed(h.left)&&isRed(h.right)) flipColor(h);
        h.N=size(h.left)+size(h.right)+1;
        return h;
    }

}
