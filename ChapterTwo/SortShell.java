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
        while (h<N/3){h=3*h+1;}//分组间隔，1，4，13，40... ... 还有其他方法目前无最优解
        while (h>=1){
            //每组排序
            for(int i=h;i<N;i++){   //i=h?

            }
        }
    }
}
