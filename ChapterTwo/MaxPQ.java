package ChapterTwo;

/**
 * 2.8 2020/7/27 16：24
 * 优先队列--Priority queue
 * API:
 *      ---------------------------------------------------------------
 *      MaxPQ()                              创建一个优先队列
 *      MaxPQ(int max)                       创建一个最大容量为max的优先队列
 *      MaxPQ(Key[] a)                       用a[]中的元素创建一个优先队列
 *      void Insert(Key v)                   向优先队列插入一个元素
 *      Key max()                            返回最大的元素
 *      Key delMax()                         删除并返回最大元素
 *      boolean isEmpty()
 *      int size()
 *      ----------------------------------------------------------------
 * 思想：用长度N+1的私有数组pa[] 来表示一个大小为N的堆
 *      插入元素：将新元素加到数组末尾，让它上浮到合适的位置
 *      删除元素：从顶端删除最大的元素，并将最后一个元素放到顶端然后下沉到合适的位置。
 *
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;   // 存放堆的数组（堆基于完全二叉树实现）
    private int N=0;    // 堆中存放的数据个数，pq[1 ... N],pq[0],未使用

    public MaxPQ(int maxN){
        pq=(Key []) new Comparable[maxN];   // 默认指向null
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i,int j){
        Key t=pq[i];pq[i]=pq[j];pq[j]=t;
    }

    //子节点大于父节点，不断上浮
    private void swim(int k){   // k 插入节点的位置
        while(k>1&&less(k/2,k)){
            exch(k/2,k);
            k/=2;
        }
    }

    private void sink(int k){
        while (2*k<=N) {                         // 子节点存在
            int j = 2 * k;                       //子节点
            if (j < N && less(j, j + 1)) j++; // 查找两个子节点最大的那一个
            if (!less(k, j)) break;             // 如果最大的子节点小于父节点K则停止
            exch(k, j);                         // 否则与最大的子节点交换
            k = j;                              // k=2*k; 继续下沉
        }
    }

    // 插入元素
    public void insert(Key v){
        pq[++N]=v;  // 放到最后一个
        sink(N);    // 上浮到合适的位置
    }

    // 删除最大的元素，并返回
    public Key delMax(){
        Key temp =pq[1];
        exch(1,N--);  // 最后一个放到首位
        pq[N+1]=null;   // 以便系统回收所占用的空间
        sink(1);    //  下沉到合适的位置
        return temp;
    }


}
