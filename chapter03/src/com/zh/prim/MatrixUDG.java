package com.zh.prim;

/**
 * Prim算法计算最生成树-基于邻接矩阵
 *
 * @author Zhaohui
 * @date 2020/4/4
 */
public class MatrixUDG {

    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   // 最大值

    /**
     * 使用顶点和邻接矩阵构造图
     *
     * @param vexs 顶点
     * @param matrix 邻接矩阵
     */
    public MatrixUDG(char[] vexs, int[][] matrix) {
        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;

        // 初始化"顶点"
        mVexs = new char[vlen];
        System.arraycopy(vexs, 0, mVexs, 0, vlen);
        // for (int i = 0; i < mVexs.length; i++)
        //     mVexs[i] = vexs[i];

        // 初始化"边"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            System.arraycopy(matrix[i], 0, mMatrix[i], 0, vlen);
            // for (int j = 0; j < vlen; j++)
            //     mMatrix[i][j] = matrix[i][j];
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
     * Prim算法计算最小生成树
     *
     * @param start 起始顶点位置
     */
    public void prim(int start) {
        int num = mVexs.length;         // 顶点个数
        int index = 0;                  // prim最小树的索引，即prims数组的索引
        char[] prims = new char[num];   // prim最小树的结果数组
        int[] weights = new int[num];   // 顶点间边的权值
        // 若weights[i]==0,说明顶点prims[i]已被加入到最小生成树
        // 若weights[i]!=0,则weights[i]表示顶点prims[i]到最小生成树中任一顶点的最小值

        // prim最小生成树中第一个数是"图中第start个顶点"，因为是从start开始的。
        prims[index++] = mVexs[start];

        // 初始化"顶点的权值数组"，
        // 将每个顶点的权值初始化为"第start个顶点"到"该顶点"的权值。
        // for (int i = 0; i < num; i++)
        //     weights[i] = mMatrix[start][i];
        System.arraycopy(mMatrix[start], 0, weights, 0, num);
        // 将第start个顶点的权值初始化为0。
        // 可以理解为"第start个顶点到它自身的距离为0",第start个顶点已被加入到最小生成树。
        weights[start] = 0;

        // 执行num-1次循环,寻找num-1个顶点
        // 因为从start开始,所以最小生成树第一个顶点实际上已经确定,所以,只需再寻找num-1个顶点
        for (int i = 0; i < num - 1; i++) {
            int k = 0; // 记录最小权值顶点
            int min = INF; // 记录最小权值
            // 在未被加入到最小生成树的顶点中，找出权值最小的顶点。
            for (int j = 0; j < num; j++) {
                // 若weights[j]=0，意味着"第j个节点已经被排序过"(或者说已经加入了最小生成树中)。
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
            }
            // 经过上面的处理后，在未被加入到最小生成树的顶点中，权值最小的顶点是第k个顶点。
            // 将第k个顶点加入到最小生成树的结果数组中
            prims[index++] = mVexs[k];
            // 将"第k个顶点的权值"标记为0，意味着第k个顶点已经排序过了(或者说已经加入了最小树结果中)。
            weights[k] = 0;
            // 当第k个顶点被加入到最小生成树的结果数组中之后，更新其它顶点的权值。
            for (int j = 0; j < num; j++) {
                // 当第j个节点没有被处理，并且需要更新时才被更新。
                if (weights[j] != 0 && mMatrix[k][j] < weights[j])
                    weights[j] = mMatrix[k][j];
            }
        }

        // 计算最小生成树的权值
        int sum = 0;
        for (int i = 1; i < index; i++) {
            int min = INF;
            // 获取prims[i]在mMatrix中的位置
            int n = getPosition(prims[i]);
            // 在vexs[0...i]中，找出到j的权值最小的顶点。
            for (int j = 0; j < i; j++) {
                int m = getPosition(prims[j]);
                if (mMatrix[m][n] < min)
                    min = mMatrix[m][n];
            }
            sum += min;
        }

        // 打印最小生成树
        System.out.printf("PRIM(%c)=%d: ", mVexs[start], sum);
        for (int i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.printf("\n");
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
        pG.prim(0);   // prim算法生成最小生成树
    }

}
