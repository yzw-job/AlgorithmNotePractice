package one.five.ChapterOne;

/**
 * id[] 数组中存放 i 上一个节点
 */
public class QuickUnion {
    private int[] id;

    QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
//  查找根节点
    private int root(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    boolean connected(int p, int q ){
        return root(p) == root(q);
    }

    void union(int p,int q){
        //id[q]=id[p];
        int i=root(p);
        int j=root(q);
        id[i]=j;    //i成为 j的子节点,j为根节点
    }

}
