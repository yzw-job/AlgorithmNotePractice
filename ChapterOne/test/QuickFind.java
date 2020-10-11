package one.five.ChapterOne;

/**
 * 快速查找算法
 * API:
 *      ------------------------------------------------------------------
 *      public class QuickFind
 *      ------------------------------------------------------------------
 *          QuickFind(int N)                    以整数标识0-N-1 初始化N个触点
 *          void union(int p , int q)           p,q添加一条连接
 *          int find(int p)                     p所在分量的的标识符
 *          boolean connected(int p, int q)     p,q 在同一分量则返回true
 *          int count()                         连通分量的数量
 *      -----------------------------------------------------------------
 */
public class QuickFind {
    private int[] id;
    QuickFind(int N)
    {
        id=new int[N];
        for (int i=0;i<N;i++)
        {
            id[i]=i;    //初始化标识符数组
        }
    }

    boolean connected(int p,int q){
        return id[p]==id[q];    //标识符相等则为联通的
    }

    void union(int p,int q){
        int pid=id[p];
        int qid=id[q];
        for(int i=0;i<id.length;i++){
            if (id[i]==pid) id[i]=qid;  //和p相同的id 全部换成q的id
        }
    }


}
