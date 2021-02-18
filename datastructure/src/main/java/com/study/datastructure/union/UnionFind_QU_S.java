package com.study.datastructure.union;

/**
 * @ClassName UnionFind_QU_S
 * @Description  基于size优化
 * @Author za-yaowei
 * @Date 2021/2/8 15:39
 * @Version 1.0
 */
public class UnionFind_QU_S extends UnionFind {

    int[] size ;

    public UnionFind_QU_S(int capacity) {
        super(capacity);
        size = new int[parent.length];
        for (int i = 0; i < parent.length; i++) {
            // 初始化默认每个节点的size都是1
            size[i] = 1;
        }
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
     *
     * 将size小的节点，嫁接到size大的根节点上
     * @param v1
     * @param v2
     */
    @Override
    void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        int size1 = size[p1];
        int size2 = size[p2];
        if (size1 < size2){
            parent[p1] = p2;
            size[p2] += size[p1];
        }else if (size1>size2){
            parent[p2] = p1;
            size[p1] += size[p2];
        }
    }
}
