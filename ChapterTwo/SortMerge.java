package ChapterTwo;

/**
 * 2.4 归并排序（自顶向下） 2020/7/26 9：43    下午13：41又来写 ^_^ 14：20完 smiling
 * 归并思想：数组分两半,且两半数组均为有序的，挨个比较left 和 mid+1 之后的元素，放到新的数组中。
 *
 */

public class SortMerge {

    private static Comparable[] temp;               // 用于存放

    public static void sort(Comparable[] a) {
        temp = new Comparable[a.length];
        sort(a, 0, a.length - 1);   //  长length 坐标为 [0----length-1]
    }

    // 重载函数，递归实现子序列的排序功能
    public static void sort(Comparable[] a, int left, int right) {
        if (right <= left) return;
        int mid = left + (right - left) / 2;
        sort(a, left, mid);             // 左半边排序 元素少的时候可以用插入排序算法不用归并
        sort(a, mid + 1, right);    // 右半边排序
        merge(a, left, mid, right);        // 归并两边

    }

    public static void merge(Comparable[] a, int left, int mid, int right) {
        //将a[left ... mid] 和 a[mid+1 ... right] 归并
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) temp[k] = a[k];  //将全部元素先放到temp中，归并到原数组a中
        // 归并到a中
        for (int k = left; k <= right; k++) {
            if (i > mid) a[k] = temp[j++];                    // i大于mid 说明左边数组用完，右边数组都是有序的，直接放入即可.j自增 指针加一
            else if (j > right) a[k] = temp[i++];             //j>right 说明右边数组游玩
            else if (less(temp[j], temp[i])) a[k] = temp[j++];//放入小的那个
            else a[k] = temp[i++];
        }
    }

    private static void show(Comparable[] a) {
        /*显示出来*/
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        //测试数组是否有序
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;  //使用comparable 接口
    }


    public static void main(String[] args) {
        String[] str = {"zed", "alice", "angel", "cc", "bed", "d", "a", "kjl","z"};
        sort(str);
        //测试代码一般会添加assert语句，确保排序后数组是有序的
        assert isSorted(str) : "sort function is wrong";
        show(str);
    }
}
