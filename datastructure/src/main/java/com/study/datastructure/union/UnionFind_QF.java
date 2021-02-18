package com.study.datastructure.union;

/**
 * @ClassName UnionFind_QF
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/8 15:39
 * @Version 1.0
 */
public class UnionFind_QF extends UnionFind {


    public UnionFind_QF(int capacity) {
        super(capacity);
    }

    /**
     *  找到该节点的根节点 （父节点就是根节点）
     * @param v
     * @return
     */
    @Override
    int find(int v) {
        return parent[v];
    }

    /**
     * 将V1所在的节点的根节点，都嫁接到V2的根节点上。
     * @param v1
     * @param v2
     */
    @Override
    void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == p1) {
                parent[i] = p2;
            }
        }
    }
}
