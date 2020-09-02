package com.study.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LRUCache
 * @Description
 * @Author za-yaowei
 * @Date 2020/9/1 9:50
 * @Version 1.0
 */
public class LRUCache {
    // 头结点
    private Node head;
    // 尾结点
    private Node end;
    // lru的容量
    private int limt;

    private Map<String, Node> cache;

    public LRUCache(int limt) {
        this.limt = limt;
        this.cache = new HashMap<>();
    }


    /**
     * 从缓存中获取数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        // 刷新结点位置
        refreshNode(node);
        return node.value;
    }

    /**
     * lrucache中的节点元素被获取或者更新时，需要调整元素在链表中的位置
     *
     * @param node
     */
    private void refreshNode(Node node) {
        // 如果操作的是尾结点，不需要调整位置
        if (node == end) {
            return;
        }
        //删掉原先结点
        removeNode(node);
        // 从尾部重新插入结点
        add(node);

    }

    /**
     * 删除结点
     *
     * @param node
     */
    private String removeNode(Node node) {
        // 删除头结点
        if (head == node) {
            head = head.next;
            // 删除尾结点
        } else if (end == node) {
            end = end.pre;
        } else {
            //删除中间结点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        return node.key;
    }


    /**
     * 向缓存中添加元素
     */
    public void put(String key, Object value) {
        Node node = cache.get(key);
        if (node == null) {
            // 表示缓存已满
            if (cache.size() >= limt) {
                // 删除头结点的元素
                String oldeKey = removeNode(head);
                cache.remove(oldeKey);
            }
            node = new Node(key, value);
            add(node);
            cache.put(key, node);
        } else {
            // 更新元素的值
            node.value = value;
            // 更新元素在链表中的位置
            refreshNode(node);

        }
    }

    /**
     *  lru删除元素
     */
    public void remove(String key){
        Node node = cache.get(key);
        if (node == null){
            return;
        }
        removeNode(node);
        cache.remove(key);
    }



    /**
     * 添加结点
     */
    public void add(Node node) {

        if (head == null) {
            head = node;
        }
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
    }


}
