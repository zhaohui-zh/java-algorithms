package com.zh.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 拓扑排序-基于邻接表
 *
 * @author Zhaohui
 * @date 2020/4/6
 */
public class ListDG {
    // 邻接表中表对应的链表的顶点
    private class ENode {
        int ivex;       // 该边所指向的顶点的位置
        ENode nextEdge; // 指向下一条弧的指针
    }

    // 邻接表中表的顶点
    private class VNode {
        char data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧
    }

    private List<VNode> mVexs;  // 顶点数组

    /**
     * 初始化邻接表
     *
     * @param vexs 顶点集
     * @param edges 边集
     */
    public ListDG(char[] vexs, char[][] edges) {

        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;
        int elen = edges.length;

        // 初始化"顶点"
        mVexs = new ArrayList<VNode>();
        for (int i = 0; i < vlen; i++) {
            // 新建VNode
            VNode vnode = new VNode();
            vnode.data = vexs[i];
            vnode.firstEdge = null;
            // 将vnode添加到数组mVexs中
            mVexs.add(vnode);
        }

        // 初始化"边"
        for (int i = 0; i < elen; i++) {
            // 读取边的起始顶点和结束顶点
            char c1 = edges[i][0];
            char c2 = edges[i][1];
            // 读取边的起始顶点和结束顶点
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);

            // 初始化node1
            ENode node1 = new ENode();
            node1.ivex = p2;
            // 将node1链接到"p1所在链表的末尾"
            if (mVexs.get(p1).firstEdge == null)
                mVexs.get(p1).firstEdge = node1;
            else
                linkLast(mVexs.get(p1).firstEdge, node1);
        }
    }

    /**
     * 将node节点链接到list最后
     *
     * @param list 链表
     * @param node 带链接顶点
     */
    private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while (p.nextEdge != null)
            p = p.nextEdge;
        p.nextEdge = node;
    }

    /**
     * 返回ch位置
     *
     * @param ch 带查找字符
     * @return
     */
    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.size(); i++)
            if (mVexs.get(i).data == ch)
                return i;
        return -1;
    }

    /**
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("== List Graph:\n");
        for (int i = 0; i < mVexs.size(); i++) {
            System.out.printf("%d(%c): ", i, mVexs.get(i).data);
            ENode node = mVexs.get(i).firstEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.ivex, mVexs.get(node.ivex).data);
                node = node.nextEdge;
            }
            System.out.printf("\n");
        }
    }

    /**
     * 拓扑排序
     *
     * <p>
     * -1 -- 失败(由于内存不足等原因导致);
     * 0 -- 成功排序，并输入结果;
     * 1 -- 失败(该有向图是有环的);
     *
     * @return 排序结果标识符
     */
    public int topologicalSort() {
        int index = 0;
        int num = mVexs.size();
        int[] ins;               // 入度数组
        char[] tops;             // 拓扑排序结果数组，记录每个节点的排序后的序号。
        Queue<Integer> queue;    // 辅组队列

        ins = new int[num];
        tops = new char[num];
        queue = new LinkedList<Integer>();

        // 统计每个顶点的入度数
        for (int i = 0; i < num; i++) {
            ENode node = mVexs.get(i).firstEdge;
            while (node != null) {
                ins[node.ivex]++;
                node = node.nextEdge;
            }
        }

        // 将所有入度为0的顶点入队列
        for (int i = 0; i < num; i++)
            if (ins[i] == 0)
                queue.offer(i);                 // 入队列

        while (!queue.isEmpty()) {              // 队列非空
            int j = queue.poll();    // 出队列。j是顶点的序号
            tops[index++] = mVexs.get(j).data;  // 将该顶点添加到tops中，tops是排序结果
            ENode node = mVexs.get(j).firstEdge;// 获取以该顶点为起点的出边队列

            // 将与"node"关联的节点的入度减1；
            // 若减1之后，该节点的入度为0；则将该节点添加到队列中。
            while (node != null) {
                // 将节点(序号为node.ivex)的入度减1。
                ins[node.ivex]--;
                // 若节点的入度为0，则将其"入队列"
                if (ins[node.ivex] == 0)
                    queue.offer(node.ivex);    // 入队列

                node = node.nextEdge;
            }
        }

        if (index != num) {
            System.out.printf("Graph has a cycle\n");
            return 1;
        }

        // 打印拓扑排序结果
        System.out.printf("== TopSort: ");
        for (int i = 0; i < num; i++)
            System.out.printf("%c ", tops[i]);
        System.out.printf("\n");

        return 0;
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'G'},
                {'B', 'A'},
                {'B', 'D'},
                {'C', 'F'},
                {'C', 'G'},
                {'D', 'E'},
                {'D', 'F'}};
        ListDG pG;

        // 自定义"图"(输入矩阵队列)
        //pG = new ListDG();
        // 采用已有的"图"
        pG = new ListDG(vexs, edges);

        pG.print();   // 打印图
        //pG.DFS();     // 深度优先遍历
        //pG.BFS();     // 广度优先遍历
        pG.topologicalSort();     // 拓扑排序
    }
}
