package com.study.datastructure.union;

/**
 * @ClassName UnionFind_QU_S
 * @Description  基于rank优化 （基于节点的树的高度优化） 路径压缩
 * @Author za-yaowei
 * @Date 2021/2/8 15:39
 * @Version 1.0
 */
public class UnionFind_QU_R_PC extends UnionFind {

    int[] rank ;

    public UnionFind_QU_R_PC(int capacity) {
        super(capacity);
        rank = new int[parent.length];
        for (int i = 0; i < parent.length; i++) {
            // 初始化默认每个节点rank都是1
            rank[i] = 1;
        }
    }

    /**
     *  找到该节点的根节点 （父节点就是根节点）
     *  在find的时候，路径上所有的节点都指向根节点。从而降低树的高度。
     * @param v
     * @return
     */
    @Override
    int find(int v) {
        rangeCheck(v);
        // 当v == parent[V]时，代表找到根节点。
        if (parent[v] != v){
            parent[v] = find(parent[v]);
        }
        return parent[v];
    }


    /**
     *
     * 将rank小的节点，嫁接到size大的根节点上
     * 将根节点低的树，嫁接到根节点高的树下面。
     * @param v1
     * @param v2
     */
    @Override
    void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        if (rank[p1] < rank[p2]) {
            parent[p1] = p2;
        }else if (rank[p1] > rank[p2]){
            parent[p2] = p1;
        }else {
            parent[p1] = p2;
            rank[p2] +=1;
        }
    }
}
