package ChapterTwo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 快速排序算法 2020/7/26 15：24 start ^_^ 我他妈的人傻了 写尼玛 卧槽 17:00 over
 */
public class SortQuick {
    private static Comparable v;

    private static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); //j 就是v 实际所在位置
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        //数组切分为：a[lo ... i-1],a[i],a[i+1 ... hi]
        int i = lo;       //左边扫描指针
        int j = hi + 1;     //右边扫描指针
        Comparable v = a[lo]; //切分元素
        while (true) {
            // 扫描左右
            while (SortInsert.less(a[++i], v)) if (i == hi) break;
            while (SortInsert.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            SortInsert.exch(a, i, j);
        }
        SortInsert.exch(a, lo, j);   //将V放到正确的地方
        return j;
    }

    public static void main(String[] args) {
        int N = 8000000;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) arr[i] = (int) (Math.random() * N);
        Date date1=new Date();
        System.out.println(date1.toString());
        SortQuick.sort(arr);
        Date date2=new Date();
        System.out.println(date2.toString());
        //测试代码一般会添加assert语句，确保排序后数组是有序的
        //assert SortInsert.isSorted(str) : "sort function is wrong";
        //SortInsert.show(arr);
    }
}
