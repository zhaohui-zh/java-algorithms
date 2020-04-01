package com.zh.core;

/**
 * 图的遍历
 *
 * @author Zhaohui
 * @date 2020/4/1
 */
public class GraphTraversal {

    public static boolean[] visited; // 记录顶点是否被访问

    /**
     * 初始化顶点访问记录数组
     */
    public static void init() {
        int num = 100;
        visited = new boolean[num];
        for (int i = 0; i < num; i++) {
            visited[i] = false;
        }
    }

    /**
     * 图的深度优先遍历
     *
     * @param graph 使用邻接矩阵存储的图
     * @param i     遍历起始节点
     */
    public static void DFS(GraphAdjacentMatrix graph, int i) {
        System.out.println(graph.vertexList[i]);
        visited[i] = true;
        for (int j = 0; j < graph.vertexNum; j++) {
            // 若某个顶点尚未被访问，则递归访问它
            if (!visited[j] && graph.adjacentMatrix[i][j] == 1)
                DFS(graph, j);
        }
    }

    /**
     * 图的深度优先遍历
     *
     * @param graph 使用邻接表存储的图
     * @param i     遍历其实结点
     */
    public static void DFS(GraphAdjacentList graph, int i) {
        GraphAdjacentList.ArcNode arcNode;
        System.out.println(graph.vertexList[i].data);
        visited[i] = true;
        arcNode = graph.vertexList[i].firstArcNode;
        while (arcNode != null) {
            int j = arcNode.adjacentVertex;
            if (!visited[j]) {
                DFS(graph, j);
            }
            arcNode = arcNode.nextArcNode;
        }
    }

    /**
     * 图的广度优先遍历
     *
     * @param graph 使用邻接矩阵存储的图
     * @param i     遍历起始节点
     */
    public static void BFS(GraphAdjacentMatrix graph, int i) {
        MyQueue<Integer> queue = new MyLinkedQueue<>();
        System.out.println(graph.vertexList[i]);
        visited[i] = true;
        queue.add(i);
        while (!queue.empty()) {
            int j = queue.remove();
            for (int k = 0; k < graph.vertexNum; k++) {
                if (!visited[k] && graph.adjacentMatrix[j][k] == 1) {
                    System.out.println(graph.vertexList[k]);
                    visited[k] = true;
                    queue.add(k);
                }
            }
        }
    }

    /**
     * 图的广度优先遍历
     *
     * @param graph 使用邻接表存储的图
     * @param i     遍历其实结点
     */
    public static void BFS(GraphAdjacentList graph, int i) {
        MyQueue<Integer> queue = new MyLinkedQueue<>();
        System.out.println(graph.vertexList[i].data);
        visited[i] = true;
        queue.add(i);
        while (!queue.empty()) {
            int j = queue.remove();
            GraphAdjacentList.ArcNode arcNode = graph.vertexList[j].firstArcNode;
            while (arcNode != null) {
                int k = arcNode.adjacentVertex;
                if (!visited[k]) {
                    System.out.println(graph.vertexList[k].data);
                    visited[k] = true;
                    queue.add(k);
                }
                arcNode = arcNode.nextArcNode;
            }
        }
    }

    public static void main(String[] args) {
        // 邻接矩阵存储的图
        GraphAdjacentMatrix graphMatrix = new GraphAdjacentMatrix();
        graphMatrix.vertexNum = 8;
        graphMatrix.arcNum = 10;
        graphMatrix.vertexList = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        graphMatrix.adjacentMatrix = new int[][]{
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 1, 1, 0},
        };
        System.out.println("DFS");
        init();
        DFS(graphMatrix, 0);
        System.out.println("BFS");
        init();
        BFS(graphMatrix, 2);

        // 邻接表存储的图
        GraphAdjacentList graphList = new GraphAdjacentList();
        graphList.vertexNum = 8;
        graphList.arcNum = 10;
        GraphAdjacentList.VertexNode[] vertexNodes = new GraphAdjacentList.VertexNode[graphList.vertexNum];
        for (int i = 0; i < graphList.vertexNum; i++) {
            GraphAdjacentList.VertexNode vertexNode = new GraphAdjacentList.VertexNode();
            vertexNode.data = i + 1;
            vertexNodes[i] = vertexNode;
        }
        graphList.vertexList = vertexNodes;

        GraphAdjacentList.ArcNode arcNode1 = new GraphAdjacentList.ArcNode();
        GraphAdjacentList.ArcNode arcNode2 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = null;
        arcNode1.adjacentVertex = 2;
        arcNode1.weigth = 1;
        arcNode2.nextArcNode = arcNode1;
        arcNode2.adjacentVertex = 1;
        arcNode2.weigth = 1;
        vertexNodes[0].firstArcNode = arcNode2;

        arcNode1 = new GraphAdjacentList.ArcNode();
        arcNode2 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = null;
        arcNode1.adjacentVertex = 4;
        arcNode1.weigth = 1;
        arcNode2.nextArcNode = arcNode1;
        arcNode2.adjacentVertex = 3;
        arcNode2.weigth = 1;
        arcNode1 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = arcNode2;
        arcNode1.adjacentVertex = 0;
        arcNode1.weigth = 1;
        vertexNodes[1].firstArcNode = arcNode1;

        arcNode1 = new GraphAdjacentList.ArcNode();
        arcNode2 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = null;
        arcNode1.adjacentVertex = 6;
        arcNode1.weigth = 1;
        arcNode2.nextArcNode = arcNode1;
        arcNode2.adjacentVertex = 5;
        arcNode2.weigth = 1;
        arcNode1 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = arcNode2;
        arcNode1.adjacentVertex = 0;
        arcNode1.weigth = 1;
        vertexNodes[2].firstArcNode = arcNode1;

        arcNode1 = new GraphAdjacentList.ArcNode();
        arcNode2 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = null;
        arcNode1.adjacentVertex = 7;
        arcNode1.weigth = 1;
        arcNode2.nextArcNode = arcNode1;
        arcNode2.adjacentVertex = 1;
        arcNode2.weigth = 1;
        vertexNodes[3].firstArcNode = arcNode2;
        vertexNodes[4].firstArcNode = arcNode2;

        arcNode1 = new GraphAdjacentList.ArcNode();
        arcNode2 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = null;
        arcNode1.adjacentVertex = 7;
        arcNode1.weigth = 1;
        arcNode2.nextArcNode = arcNode1;
        arcNode2.adjacentVertex = 2;
        arcNode2.weigth = 1;
        vertexNodes[5].firstArcNode = arcNode2;
        vertexNodes[6].firstArcNode = arcNode2;

        arcNode1 = new GraphAdjacentList.ArcNode();
        arcNode2 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = null;
        arcNode1.adjacentVertex = 6;
        arcNode1.weigth = 1;
        arcNode2.nextArcNode = arcNode1;
        arcNode2.adjacentVertex = 5;
        arcNode2.weigth = 1;
        arcNode1 = new GraphAdjacentList.ArcNode();
        arcNode1.nextArcNode = arcNode2;
        arcNode1.adjacentVertex = 4;
        arcNode1.weigth = 1;
        arcNode2 = new GraphAdjacentList.ArcNode();
        arcNode2.nextArcNode = arcNode1;
        arcNode2.adjacentVertex = 3;
        arcNode2.weigth = 1;
        vertexNodes[7].firstArcNode = arcNode2;

        System.out.println("DFS");
        init();
        DFS(graphList, 0);
        System.out.println("BFS");
        init();
        BFS(graphList, 2);
    }

}
