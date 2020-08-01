package ChapterThree;

/**
 * 3.2  2020/8/1
 * 基于有序数组的二分查找
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int N; // 数量

    BinarySearchST(Key[] keys,int capacity){
        this.keys=keys;
        values= (Value[]) new Comparable[capacity];
    }
    /**
     * rank 返回小于给定键的数量
     *      有序数组来说，该键所在的位置==小于数量
     * @param key
     * @param lo
     * @param hi
     * @return
     */
    public int rank(Key key,int lo,int hi){
        //递归实现

        if(hi<lo)return lo; // 递归头 未命中
        int mid = lo + (hi - lo) / 2;
        if (less(key,keys[mid])) return rank(key, lo, mid - 1);
        else if (less(keys[mid],key)) return rank(key, mid + 1, hi);
        else return mid;
    }

    private boolean less(Key key, Key key1) {
        return key.compareTo(key1)<0;
    }

    public static void main(String[] args) {

        Integer[] arr={0,1,2,4,5,8};
        BinarySearchST<Integer,Integer> bs=new BinarySearchST(arr,6);
        System.out.println(bs.rank(1,0,5));

    }

}
