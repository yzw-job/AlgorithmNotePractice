package ChapterTwo;

import java.security.cert.CertificateParsingException;

/** 2.1
 * 排序类的算法模板 example, 排序算法实现
 * API:
 *      -------------------------------------------------------------
 *      public static void sort()                       排序算法
 *      private static boolean less()                   对元素进行比较
 *      private static void exch()                      交换元素
 *      private static void show()                      显示
 *      public static boolean isSorted()                测试元组是否有序
 *      -------------------------------------------------------------
 * * * sort()函数以选择排序算法实现：
 * 即查找元素最小值，放到是首位，然后 i+1 循环此操作。缺点：无论数组前面部分是否为有序数列都要遍历一遍数组。
 */
public class ExampleOfSort {
    public static void sort(Comparable[] a) {
        //详见具体算法
        //选择排序实现
        int N=a.length;
        for (int i=0;i<N;i++){
            int min=i;
            for (int j=i+1;j<N;j++){
                if(less(a[j],a[min]))min=j;
            }
            exch(a,i,min);
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
        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
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

    //main() 测试用例
    public static void main(String[] args) {
        String[] str = {"zed", "alice", "angel", "cc", "bed"};
        sort(str);
        assert isSorted(str);    //测试代码一般会添加assert语句，确保排序后数组是有序的
                                //open: run->edit configurations->vm options-> -ea
        show(str);
    }
}
