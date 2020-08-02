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
 *      Value get(Key key)
 *      void put(Key key,Value value)
 *      delete(Key key)
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
        int i=rank(key);
        if(i<N&&key.compareTo(keys[i])==0){ // 能找到
            return values[i];
        }
        else return null;
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

        // 未找到 i 为插入位置，其他向后移位置
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
    public Key select(int k){
        if(isEmpty()){
            System.out.println("Table is empty");
            return null;
        }
        if (0<=k&&k<N){
            return keys[k];
        }
        System.out.println("key out of index");
        return null;

    }

    public Key ceiling(Key key){
        int i =rank(key);
        if(i>=N) return null;
        return keys[i];
    }
    public Key floor(Key key){
        int i =rank(key);
        if(i>=N) return keys[N-1];
        if(i==0)return null;
        return keys[i-1];
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

        // construct symbol table <Sting , Integer>
        String[] arr={"b","m","h","d","f","e"};
        BinarySearchST<String,Integer> bs=new BinarySearchST(6);
        bs.put(arr[0],1);
        bs.put(arr[1],2);
        bs.put(arr[2],3);
        bs.put(arr[3],4);
        bs.put(arr[4],5);
        bs.put(arr[5],6);

        // function test
        System.out.println("max key is : "+bs.max());
        System.out.println("min key is : "+bs.min());
        System.out.println("select 3th key is : "+bs.select(0));
        System.out.println("ceiling a is :" + bs.ceiling("a"));
        System.out.println("floor a is :" + bs.floor("a"));
        System.out.println("find a is :" + bs.get("a"));
        // 迭代器遍历
        for (Iterator<String> it = bs.iterator(); it.hasNext();){
            System.out.print(it.next()+" ");
        }


    }

}