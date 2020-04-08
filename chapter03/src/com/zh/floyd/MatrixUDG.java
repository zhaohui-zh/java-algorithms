package com.zh.floyd;

/**
 * Floyd最短路径算法-基于邻接矩阵
 *
 * @author Zhaohui
 * @date 2020/4/7
 */
public class MatrixUDG {
    private int mEdgNum;        // 边的数量
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   // 最大值

    /**
     * 使用给定的数据创建图
     *
     * @param vexs   顶点集
     * @param matrix 邻接矩阵
     */
    public MatrixUDG(char[] vexs, int[][] matrix) {
        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;

        // 初始化"顶点"
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];

        // 初始化"边"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = matrix[i][j];

        // 统计"边"
        mEdgNum = 0;
        for (int i = 0; i < vlen; i++)
            for (int j = i + 1; j < vlen; j++)
                if (mMatrix[i][j] != INF)
                    mEdgNum++;
    }

    /**
     * 返回顶点ch的位置
     *
     * @param ch 顶点
     * @return
     */
    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++)
            if (mVexs[i] == ch)
                return i;
        return -1;
    }

    /**
     * 返回顶点v的第一个邻接顶点的索引，失败则返回-1
     *
     * @param v 顶点
     * @return
     */
    private int firstVertex(int v) {
        if (v < 0 || v > (mVexs.length - 1))
            return -1;
        for (int i = 0; i < mVexs.length; i++)
            if (mMatrix[v][i] != 0 && mMatrix[v][i] != INF)
                return i;
        return -1;
    }

    /**
     * 返回顶点v相对于w的下一个邻接顶点的索引，失败则返回-1
     *
     * @param v 顶点v
     * @param w 顶点w
     * @return
     */
    private int nextVertex(int v, int w) {
        if (v < 0 || v > (mVexs.length - 1) || w < 0 || w > (mVexs.length - 1))
            return -1;
        for (int i = w + 1; i < mVexs.length; i++)
            if (mMatrix[v][i] != 0 && mMatrix[v][i] != INF)
                return i;
        return -1;
    }

    /**
     * 获取图中的边
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges;

        edges = new EData[mEdgNum];
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = i + 1; j < mVexs.length; j++) {
                if (mMatrix[i][j] != INF) {
                    edges[index++] = new EData(mVexs[i], mVexs[j], mMatrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 对边按照权值大小进行排序(由小到大)
     *
     * @param edges
     * @param elen
     */
    private void sortEdges(EData[] edges, int elen) {
        for (int i = 0; i < elen; i++) {
            for (int j = i + 1; j < elen; j++) {
                if (edges[i].weight > edges[j].weight) {
                    // 交换"边i"和"边j"
                    EData tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    /**
     * 获取顶点i的终点
     *
     * @param vends 最短路径表
     * @param i     顶点
     * @return
     */
    private int getEnd(int[] vends, int i) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }

    private static class EData {
        char start; // 边的起点
        char end;   // 边的终点
        int weight; // 边的权重

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    /**
     * Floyd最短路径
     *
     * @param path 路径。pahth[i[[j]=k表示顶点i到顶点j的最短路径经过顶点k
     * @param dist 距离。dist[i][j]=sum表示顶点i到顶点k的最短距离是sum
     */
    public void floyd(int[][] path, int[][] dist) {
        // 初始化
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                path[i][j] = j;
                dist[i][j] = mMatrix[i][j];
            }
        }
        // 计算最短路径
        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                    int temp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : dist[i][k] + dist[k][j];
                    if (temp < dist[i][j]) {
                        // "i到j最短路径"对应的值设为更小的一个(即经过k)
                        dist[i][j] = temp;
                        // "i到j最短路径"对应的路径，经过k
                        path[i][j] = k;
                    }
                }
            }
        }
        // 打印floyd最短路径的结果
        System.out.print("floyd: \n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%2d  ", dist[i][j]);
            System.out.print("\n");
        }
    }


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        MatrixUDG pG;

        // 自定义"图"(输入矩阵队列)
        //pG = new MatrixUDG();
        // 采用已有的"图"
        pG = new MatrixUDG(vexs, matrix);

        //pG.print();   // 打印图
        //pG.DFS();     // 深度优先遍历
        //pG.BFS();     // 广度优先遍历
        //pG.prim(0);   // prim算法生成最小生成树
        //pG.kruskal(); // Kruskal算法生成最小生成树

        int[][] path = new int[pG.mVexs.length][pG.mVexs.length];
        int[][] floy = new int[pG.mVexs.length][pG.mVexs.length];
        // floyd算法获取各个顶点之间的最短距离
        pG.floyd(path, floy);
    }

}
