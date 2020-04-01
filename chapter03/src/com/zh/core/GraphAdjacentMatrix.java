package com.zh.core;

/**
 * 图的邻接矩阵存储结构
 *
 * @author Zhaohui
 * @date 2020/4/1
 */
public class GraphAdjacentMatrix {
    public int vertexNum; // 顶点数
    public int arcNum; // 弧数
    public int[] vertexList; //顶点表
    public int[][] adjacentMatrix; //邻接矩阵
}
