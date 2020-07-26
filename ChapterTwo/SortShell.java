package ChapterTwo;

/**
 * 2.3
 * 希尔排序算法
 * 思想：右边大于左边，左右交换
 * API:
 *      -------------------------------------------------------------
 *      public static void sort()                       排序算法
 *      其他同
 *      -------------------------------------------------------------
 */
public class SortShell {
    public static void sort(Comparable[] a){
        int N=a.length;
        int h=1;
        while (h<N/3){h=3*h+1;}//分组间隔，1，4，13，40... ... 还有其他方法N/2等
        while (h>=1){
            //每组排序
            for(int i=h;i<N;i++){   //i=h? 类似插入排序i=1开始，h[0] h[1] ... 从h[1]开始
                for (int j=i;j>=h&&less(a[j],a[j-h]);j-=h){
                    exch(a,j,j-h);                      //使用冒泡法进行 改成插入排序算法比较好
                }
            }
            //再次分组
            h/=3;
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;  //使用comparable 接口
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
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

    public static void main(String[] args) {
        String[] str = {"zed", "alice", "angel", "cc", "bed"};
        SortShell.sort(str);
        //测试代码一般会添加assert语句，确保排序后数组是有序的
        assert SortShell.isSorted(str):"sort function is wrong";
        SortShell.show(str);
    }
}
