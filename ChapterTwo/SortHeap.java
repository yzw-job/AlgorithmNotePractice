package ChapterTwo;

import java.util.Date;

/**
 * 2.10
 * 2020/7/30 14：22
 * 堆排序
 * 思想：①构造堆 从N/2节点开始 sink ②堆排序,最大元素删除，堆变小，在删除，直到堆消失
 *       和选择排序类似但所需的比较少很多，堆排序提供了一种，从未排序部分找最大元素的放法
 *
 */
public class SortHeap<Item extends Comparable<Item>>{

    /*private Item[] que;   // 排序传入一个数组，不需要自己定义
    SortHeap(int maxN){
        que = (Item[]) new Comparable[maxN];
    }*/

    private void sink(Item[] a,int k,int N){
        while (2*k<=N){
            int j=2*k;
            if(j<N&&less(a,j,j+1))j++;
            if(less(a,j,k))break;
            exch(a, k, j);
            k=j;
        }
    }

    private void exch(Item[] a, int k, int j) {
        Item item=a[k];
        a[k]=a[j];
        a[j]=item;
    }

    private boolean less(Comparable[] a,int j, int i) {
        return a[j].compareTo(a[i])<0;
    }

    public void sort(Item[] a) {
        int N=a.length-1;
//        int N=5;

        // 堆构造 2/n处开始
        for(int k = N/2; k>=1; k--){
            sink(a, k, N);
        }

        // 排序
        while(N>1){
            exch(a,1,N--);
            sink(a,1,N);
        }
    }

    public static void main(String[] args) {
        // test
        SortHeap<Integer>  sh=new SortHeap();
        Integer[] arr={10,1,9,4,8,5,8,2,8,7,5,5,12,1,5,9,4};   //  第一个元素不参与排序
        sh.sort(arr);
        for (int i=0;i<arr.length;i++) System.out.print(" "+arr[i]);
        System.out.println();
        // test
        /*int N = 8000000; // 10 seconds
        Integer[] arr2 = new Integer[N];
        for (int i = 0; i < N; i++) arr2[i] = (int) (Math.random() * N);
        Date date1=new Date();
        System.out.println(date1.toString());
        sh.sort(arr2);
        Date date2=new Date();
        System.out.println(date2.toString());*/
    }


}
