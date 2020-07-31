package CodePractice;

import java.util.Comparator;

public class TestSortMerge<Item> {
    private Item[] temp;               // 用于存放

    public void sort(Item[] a, Comparator c) {
        temp = (Item[])new Object[a.length];
        sort( a, 0, a.length - 1,c);   //  长length 坐标为 [0----length-1]
    }

    // 重载函数，递归实现子序列的排序功能
    public  void sort(Item[] a, int left, int right,Comparator c) {
        if (right <= left) return;
        int mid = left + (right - left) / 2;
        sort(a, left, mid,c);             // 左半边排序 元素少的时候可以用插入排序算法不用归并
        sort(a, mid + 1, right,c);    // 右半边排序
        merge(a, left, mid, right, c);        // 归并两边

    }

    public  void merge(Item[] a, int left, int mid, int right,Comparator c) {
        //将a[left ... mid] 和 a[mid+1 ... right] 归并
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) temp[k] = a[k];  //将全部元素先放到temp中，归并到原数组a中
        // 归并到a中
        for (int k = left; k <= right; k++) {
            if (i > mid) a[k] = temp[j++];                    // i大于mid 说明左边数组用完，右边数组都是有序的，直接放入即可.j自增 指针加一
            else if (j > right) a[k] = temp[i++];             //j>right 说明右边数组游玩
            else if (less(temp[j], temp[i],c)) a[k] = temp[j++];//放入小的那个
            else a[k] = temp[i++];
        }
    }

    private boolean less(Item a, Item b, Comparator c){
        return c.compare(a,b)<0;
    }


}
