package ChapterTwo;

/**
 * 2.2
 * 插入排序，相比选择排序，对某些元素有序或接近有序的数组将会比对随机顺序的数组或是逆序数组快得多
 * 冒泡思想：右边大于左边，左右交换
 * 插入思想：每次从未排序的元素取出第一个，与已排序的元素从尾到头逐一比较，找到插入点，将之后的元素都往后移一位，腾出位置给该元素。
 * API:
 *      -------------------------------------------------------------
 *      public static void sort()                       排序算法
 *      private static boolean less()                   对元素进行比较
 *      private static void exch()                      交换元素
 *      private static void show()                      显示
 *      public static boolean isSorted()                测试元组是否有序
 *      -------------------------------------------------------------
 */
public class SortInsert {
    public static void sort(Comparable[] a) {
        //详见具体算法
        //冒泡实现
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }

        }
    }

    public static void sortinsert(Comparable[] a){
        int N=a.length;
        for (int i=1;i<N;i++){
            Comparable temp =a[i];
            int j=i-1;
            for (;j>=0;j--){
                if(less(temp,a[j])){a[j+1]=a[j];}//查找加入的位置
                else break;
            }
            a[j+1]=temp;
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
        SortInsert.sortinsert(str);
        //测试代码一般会添加assert语句，确保排序后数组是有序的
        assert SortInsert.isSorted(str):"sort function is wrong";
        SortInsert.show(str);
    }
}
