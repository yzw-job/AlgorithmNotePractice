package ChapterTwo;

import java.security.PublicKey;

/**
 * 2.7
 * 三向切分的快速排序 对于具有大量重复的元素性能较好
 *
 */
public class SortQiuck3way {
    public static void sort(Comparable[] a,int lo,int hi){

        if(lo>=hi)return;
        int lt=lo;      //a[lo ---- lt-1]小于v
        int i=lo+1;     //a[lt---i-1]==v
        int gt=hi;      //a[i----hi]>v
        Comparable v=a[lo];
        while (i<=gt){
            int cmp=a[i].compareTo(v);
            if(cmp<0)SortInsert.exch(a,lt++,i++);  //a[i]<v 放到gt
            else if(cmp>0)SortInsert.exch(a,gt--,i);
            else i++;
        }
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
    }
}
