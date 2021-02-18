package com.study.datastructure.union;

/**
 * @ClassName UnionFind_QU
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/8 15:39
 * @Version 1.0
 */
public class UnionFind_QU extends UnionFind {


    public UnionFind_QU(int capacity) {
        super(capacity);
    }

    /**
     *  找到该节点的根节点 （父节点就是根节点）
     * @param v
     * @return
     */
    @Override
    int find(int v) {
        rangeCheck(v);
        // 当v == parent[V]时，代表找到根节点。
        while (parent[v] != v){
            // 递归向上去找父节点。
            v = find(v);
        }
        return  v;
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

        parent[p1] = p2;
    }
}
