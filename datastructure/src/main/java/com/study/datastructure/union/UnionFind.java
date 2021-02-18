package com.study.datastructure.union;

/**
 * @ClassName UnionFind
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/8 15:25
 * @Version 1.0
 */
public abstract class UnionFind {

    protected int[] parent;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity is must >0");
        }

        parent = new int[capacity];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }


    /**
     * 查找v所属的结合
     * @param v
     * @return
     */
    abstract int find(int v);

    /**
     *  合并v1  v2所属的集合
     * @param v1
     * @param v2
     */
    abstract void union(int v1, int v2);



    boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (v < 0 || v > parent.length){
            throw new IllegalArgumentException("参数错误");
        }
    }
}
