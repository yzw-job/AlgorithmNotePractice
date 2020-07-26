package ChapterTwo;

/**
 * 2.5 自底向上的归并排序 2020/7/26 14：54--15:02
 * 思想：先两两归并，然后四四归并，然后八八 ... ...
 */
public class SortMergeBottem {
    private static Comparable[] temp;

    public static void sort(Comparable[] a){
        temp=new Comparable[a.length];
        for(int sz=1;sz<a.length;sz+=sz){  //sz 子数组大小
            for(int low=0;low<a.length-sz;low+=sz+sz)
                SortMerge.merge(a,low,low+sz-1,Math.min(low+sz+sz-1,a.length-1));
                                                    //防止溢出
        }
    }

    public static void main(String[] args) {
        String[] str = {"zed", "alice", "angel", "cc", "bed", "d", "a", "kjl","z"};
        sort(str);
        //测试代码一般会添加assert语句，确保排序后数组是有序的
        assert SortInsert.isSorted(str) : "sort function is wrong";
        SortInsert.show(str);
    }
}
