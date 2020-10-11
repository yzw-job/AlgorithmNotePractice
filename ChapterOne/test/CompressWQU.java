package one.five.ChapterOne;

public class CompressWQU {
    private int[] id;   //对应的根节点
    private int[] sz;   //各个根节点对应数量大小
    private int count;//连通分量的数量

    CompressWQU(int N) {
        count = N;
        sz = new int[N];    //存放树的个数
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    //  查找根节点
    private int root(int i) {
        while (id[i] != i) {
            //i = id[i];
            System.out.println("查找  "+i);
            int temp = id[i];
            id[i] = id[id[i]];
            i = temp;
            System.out.println("查找  "+i);
        }
        return i;
    }

    boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);
//      id[i] = j;    //i成为 j的子节点,j为根节点
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public static void main(String[] args) {
        CompressWQU w1 = new CompressWQU(10);
        w1.union(1, 2);
        w1.union(2, 3);

        w1.union(0,5);
        w1.union(0,6);

        w1.union(6,1);

        System.out.println("gen :"+w1.root(4));
        System.out.println("gen :"+w1.count);
        for(int i=0;i<10;i++){
            System.out.println(""+w1.id[i]);
        }
    }
}
