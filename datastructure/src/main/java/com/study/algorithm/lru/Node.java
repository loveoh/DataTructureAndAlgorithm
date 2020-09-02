package com.study.algorithm.lru;

/**
 * @ClassName Node
 * @Description
 * @Author za-yaowei
 * @Date 2020/9/1 9:44
 * @Version 1.0
 */
public class Node {

    public String key;

    public Object value;

    // 前指针
    public Node pre;
    // 后指针
    public Node next;

    public Node(String key, Object value) {
        this.key = key;
        this.value = value;
    }


}
