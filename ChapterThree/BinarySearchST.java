package ChapterThree;

import java.util.Iterator;

/**
 * 3.2  2020/8/1
 * 基于有序数组的二分查找
 * API:
 *      -----------------------------------------------------------------
 *      int rank(Key key,int lo,int hi)        列表中小于key的元素个数（key的序号）
 *      Key min()
 *      Key max()
 *      Key select(int k)                       第K个键值
 *      Key floor(Key key)                      小于key 的最大值
 *      Key ceiling(Key key)                    大于key 的最小值
 *
 *      --------------------------------------------------------------------
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int N; // 数量

    BinarySearchST(int capacity){
        // 可以动态调整数组大小
        this.keys=(Key[]) new Comparable[capacity];
        this.values= (Value[]) new Comparable[capacity];
    }

    int size(){return N;}

    /**
     * 获得key 对应的元素，没有则返回null
     */
    public Value get(Key key){
        if(isEmpty())return null;
        int i = rank(key);
        if(i>=N)return null;
        else return values[i];
    }

    /**
     * 按顺序放入列表
     * 查找对应的键值key,找到放入，没有则新建列表
     */
    public void put(Key key,Value value){
        int i=rank(key);
        if(i<N&&key.compareTo(keys[i])==0){ // 能找到
            values[i]=value;return;
        }

        // 未找到
        for(int j=N;j>i;j--){
            keys[j]=keys[j-1];
            values[j]=values[j-1];
        }
        keys[i]=key;
        values[i]=value;
        N++;
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

        if(hi<lo)return lo; // 递归头 未命中，lo 即为所有大于查找的元素，的最小元素 a[lo-1]<value,a[lo]>value
                            // 对于value 小于 所有元素lo=0;大于所有元素，lo=hig+1;
        int mid = lo + (hi - lo) / 2;
        if (less(key,keys[mid])) return rank(key, lo, mid - 1);
        else if (less(keys[mid],key)) return rank(key, mid + 1, hi);
        else return mid;
    }
    public int rank(Key key){
        return rank(key,0,N-1);
    }

    private boolean less(Key key, Key key1) {
        return key.compareTo(key1)<0;
    }

    private boolean isEmpty(){return N==0;}

    public Key min(){return keys[0];}
    public Key max(){return keys[N-1];}
    public Key select(int k){return keys[k];}
    public Key ceiling(Key key){
        return key;
    }
    public Key floor(Key key){
        return key;
    }

    // 实现迭代器
    public Iterator<Key> iterator(){
        return new STIterator();
    }
    /**
     * 私有内部类 实现迭代器的接口
     */
    private class STIterator implements Iterator<Key>{
        private int i=0; //N为个数 i 为索引
        @Override
        public boolean hasNext() {
            return i<N;
        }

        @Override
        public Key next() {

            return keys[i++];//N为个数 i 为索引
        }
    }


    public static void main(String[] args) {

        Integer[] arr={0,1,2,4,5,8};
        BinarySearchST<Integer,Integer> bs=new BinarySearchST(6);
        System.out.println(bs.rank(10,0,5));

        // 迭代器遍历
        for (Iterator<Integer> it = bs.iterator();it.hasNext();){
            System.out.print(it.next()+" ");
        }


    }

}
