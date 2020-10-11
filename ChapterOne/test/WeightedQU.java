package one.five.ChapterOne;

public class WeightedQU {
    private int[] id;   //对应的根节点
    private int[] sz;   //各个根节点对应数量大小
    private int count;//连通分量的数量

    WeightedQU(int N) {
        count=N;
        sz = new int[N];    //存放树的个数
        id = new int[N];
        for (int i = 0; i < N; i++)
        {   id[i] = i;
            sz[i]=1;
        }
    }

    //  查找根节点
    private int root(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    void union(int p, int q) {
        //id[q]=id[p];
        int i = root(p);
        int j = root(q);
//      id[i] = j;    //i成为 j的子节点,j为根节点
        if(i==j)return;
        if(sz[i]<sz[j]){
            id[i]=j;
            sz[j]+=sz[i];
        }
        else {id[j]=i;sz[i]+=sz[j];}
        count--;
    }

    public static void main(String[] args) {
        WeightedQU w1=new WeightedQU(10);
        w1.union(4,3);
        w1.union(3,8);
        w1.union(6,5);
        w1.union(9,4);
        w1.union(2,1);
        w1.union(5,0);
        w1.union(7,2);
        w1.union(6,1);
        System.out.println(""+w1.connected(0,9)+"  "+w1.count);
    }
}
