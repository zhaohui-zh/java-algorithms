package com.zh.kruskal;

/**
 * Kruskal算法计算最小生成树-基于邻接矩阵
 *
 * @author Zhaohui
 * @date 2020/4/6
 */
public class MatrixUDG {
    private int mEdgNum;        // 边的数量
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   // 最大值

    /**
     * 使用顶点和邻接矩阵构造图
     *
     * @param vexs   顶点
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
     * 获取指定顶点的位置
     *
     * @param ch 顶点
     * @return 顶点的位置
     */
    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++)
            if (mVexs[i] == ch)
                return i;
        return -1;
    }

    /**
     * Kruskal算法计算最小生成树
     */
    public void kruskal() {
        int index = 0;                      // rets数组的索引
        int[] vends = new int[mEdgNum];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
        EData[] rets = new EData[mEdgNum];  // 结果数组，保存kruskal最小生成树的边
        EData[] edges;                      // 图对应的所有边

        // 获取"图中所有的边"
        edges = getEdges();
        // 将边按照"权"的大小进行排序(从小到大)
        sortEdges(edges, mEdgNum);

        for (int i = 0; i < mEdgNum; i++) {
            int p1 = getPosition(edges[i].start);      // 获取第i条边的"起点"的序号
            int p2 = getPosition(edges[i].end);        // 获取第i条边的"终点"的序号

            int m = getEnd(vends, p1);                 // 获取p1在"已有的最小生成树"中的终点
            int n = getEnd(vends, p2);                 // 获取p2在"已有的最小生成树"中的终点
            // 如果m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
            if (m != n) {
                vends[m] = n;                       // 设置m在"已有的最小生成树"中的终点为n
                rets[index++] = edges[i];           // 保存结果
            }
        }
        // 统计并打印"kruskal最小生成树"的信息
        int length = 0;
        for (int i = 0; i < index; i++)
            length += rets[i].weight;
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++)
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        System.out.printf("\n");
    }

    /**
     * 获取图中所有的边
     *
     * @return 边集
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[mEdgNum];
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
     * @param edges 边集
     * @param elen  边的数量
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
     * 获取i的终点
     *
     * @param vends "已有最小生成树"中每个顶点在该最小树中的终点
     * @param i     顶点索引
     * @return i的终点
     */
    private int getEnd(int[] vends, int i) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }

    /**
     * 边的结构体
     */
    private static class EData {
        char start; // 边的起点
        char end;   // 边的终点
        int weight; // 边的权重

        // 索引小的为起点，大的为终点
        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
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
        pG.kruskal();   // Kruskal算法生成最小生成树
    }
}
