package com.study.datastructure.skiplist;

import java.util.Comparator;

/**
 * @ClassName SkipList
 * @Description
 * @Author za-yaowei
 * @Date 2021/3/1 14:51
 * @Version 1.0
 */
public class SkipList<K, V> {
    /**
     * 调表的最大层数，默认32层
     */
    private static int MAX_LEVEL = 32;

    /**
     * 跳表增加一层的概率
     */
    private static double P = 0.25;
    /**
     * 有效层数
     */
    private int level;
    private int size;
    /**
     * 虚拟头结点
     */
    private Node<K, V> first;

    private Comparator<K> comparator;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        first = new Node<>(null,null,MAX_LEVEL);
    }

    public SkipList() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    /**
     *  根据key 获取skipList中的元素
     * @param key
     * @return
     */
    public V get(K key) {
        checkKey(key);

        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) return node.nexts[i].value;
        }
        return null;
    }

    /**
     *  新增元素
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        checkKey(key);
        Node<K,V> node = first;
        // 存放目标节点的前前驱结点
        Node[] prevNodes = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            // 找到当前节点，进行更新。
            if (cmp == 0) {
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            prevNodes[i] = node;
        }
        // 生成新节点跳表的层数
        int newLevel = randomLevel();
        Node<K,V> newNode = new Node<>(key,value,newLevel);
        for (int i = 0 ; i < newLevel; i++) {
            if (i >= level){
                first.nexts[i] = newNode;
            }else {
                newNode.nexts[i] = prevNodes[i].nexts[i];
                prevNodes[i].nexts[i] = newNode;
            }
        }
        //节点数量增加
        size++;
        // 更新跳表的层级
        level = Math.max(newLevel,level);

        return null;
    }

    /**
     *  删除元素
     * @param key
     * @return
     */
    public V remove(K key) {
        checkKey(key);

        Node<K, V> node = first;
        // 保存前驱结点
        Node[] prev = new Node[level];
        boolean flag = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            prev[i] = node;
            if (cmp == 0) {
                flag = true;
            }
        }
        // 表示要删除的节点不存在
        if (!flag) return null;

        // 待删除的节点
        Node<K,V> removeNode = node.nexts[0];
        size--;
        // 修改前驱结点的后继指针。
        for (int i = 0; i < removeNode.nexts.length; i++) {
            prev[i].nexts[i] = removeNode.nexts[i];
        }

        int newLevel = level;
        // 更新跳表的层级
        while (--newLevel >= 0 && first.nexts[newLevel] == null){
            level = newLevel;
        }

        return removeNode.value;
    }



    /**
     *  随机生成跳表的层数
     * @return
     */
    private int randomLevel() {
        int level = 1;
        if (Math.random() < P && level < MAX_LEVEL ){
            level++;
        }
        return level;

    }


    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1,k2) : ((Comparable<K>)k1).compareTo(k2);

    }

    private void checkKey(K key) {
        if (key == null) throw new IllegalArgumentException("key is not null");
    }


    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }
    }

}
