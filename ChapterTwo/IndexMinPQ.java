package ChapterTwo;

import java.sql.SQLOutput;

/**
 * 2.9 2020//29 14：19
 * 索引优先队列
 * 优先队列有一个缺点，就是不能直接访问已存在于优先队列中的对象，并更新它们。
 * 索引优先队用一个整数和对象进行关联，当我们需要跟新该对象的值时，可以通这个整数进行快速索引，然后对对象的值进行更新。
 * 更新后的对象在优先队列中的位置可能发生变化，这样以保证整个队列还是一个优先队列。
 */
public class IndexMinPQ<Key extends Comparable<Key>> {
    private int N;          // PQ 中的元素数量
    private int[] pq;       // 索引二叉堆，由1开始
    private int[] qp;       // 逆序 pq[qp[i]]=i=qp[pq[i]];
    private Key[] elements;     // 有优先级之分的元素

    public IndexMinPQ(int maxN) {
        //cKeys = new Key[maxN];
        elements = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void exch(int a, int b) {

        // elements 对应的数值key交换,以及key 的位置交换
        int pq_ex = pq[a];
        pq[a] = pq[b];
        pq[b] = pq_ex;
        int qp_ex = qp[pq[a]];
        qp[pq[a]] = qp[pq[b]];
        qp[pq[b]] = qp_ex;

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

    private boolean less(int a, int b) {
        return elements[pq[a]].compareTo(elements[pq[b]])>0;
    }

    public void insert(int k,Key element){
        N++;
        qp[k]=N;    // 保存k的位置 N
        pq[N]=k;    // pq后尾顺序添加K（element对应的值）
        elements[k]=element;
        swim(N);    //  上浮到某个位置
    }

    public int delMax(){
        int indexOfMax=pq[1]; // 返回的最大的元素 对应的数值
        exch(1,N--);        // 最后一个放到首位
        sink(1);            //下沉
        elements[pq[N+1]]=null;
        qp[pq[N+1]]=-1;
        return indexOfMax;
    }
    public void Max(){
        int indexOfMax=pq[1]; // 返回的最大的元素 对应的数值
        System.out.println("max vlaue is "+elements[pq[1]]);
    }
    public void change(int key,Key element){
        elements[key]=element;
        swim(qp[key]);
        sink(qp[key]);
    }
    void show(){
        for (int i=0;i<11;i++){
            System.out.print(" "+i+elements[i]);
        }
        System.out.println();
        for (int i=0;i<11;i++){
            System.out.print(" "+pq[i]);
        }
        System.out.println();
        for (int i=0;i<11;i++){
            System.out.print(" "+qp[i]);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        IndexMinPQ Maxx=new IndexMinPQ(10);
        Maxx.insert(1,"k");
        Maxx.insert(3,"f");
        Maxx.insert(4,"n");
        Maxx.insert(6,"c");
        Maxx.insert(8,"h");
        Maxx.show();
//        int j=Maxx.delMax();
//        System.out.println(j);
//        Maxx.show();
//        System.out.println(Maxx.N);
        Maxx.change(4,"a");

        Maxx.show();
        Maxx.Max();

    }

}
