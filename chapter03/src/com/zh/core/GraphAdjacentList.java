package com.zh.core;

/**
 * 图的邻接表存储结构
 *
 * @author Zhaohui
 * @date 2020/4/1
 */
public class GraphAdjacentList {

    public int vertexNum; // 顶点数
    public int arcNum; // 弧数
    public VertexNode[] vertexList; // 顶点表

    /**
     * 弧结点
     */
    public static class ArcNode {
        public int adjacentVertex; // 该弧所指向的顶点位置
        public ArcNode nextArcNode; // 指向下一条弧的指针
        public int weigth; // 该弧上的权值
    }

    /**
     * 顶点结点
     */
    public static class VertexNode {
        public int data; // 顶点的数据
        public ArcNode firstArcNode; // 指向第一条弧
    }

}
