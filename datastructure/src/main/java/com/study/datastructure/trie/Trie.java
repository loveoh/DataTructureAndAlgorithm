package com.study.datastructure.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName trie
 * @Description 字典树
 * @Date 2021/1/28 17:21
 * @Version 1.0
 */
public class Trie<V> {
    // 保存该字典树下存储的单词数
    private int size;

    private Node<V> root;


    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void clear() {
        size = 0;
        root = null;
    }

    /**
     * 是否包含该单词
     *
     * @param str
     * @return
     */
    boolean contains(String str) {
        Node<V> node = node(str);
        return node != null && node.word;
    }

    private Node<V> node(String str) {
        keyCheck(str);
        if (root == null) return null;

        int length = str.length();
        Node<V> node = root;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            Node<V> vNode = node.children.get(c);
            if (vNode == null) {
                return null;
            }
            node = node.children.get(c);
        }

        return node;
    }


    /**
     * 新增单词
     *
     * @param str
     * @param value
     * @return
     */
    V add(String str, V value) {
        keyCheck(str);
//        char[] chars = str.toCharArray();
        int length = str.length();
        V oldValue = value;
        if (root == null) {
            root = new Node<>(null);
        }
        Node<V> node = root;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            boolean childFlag = node.children == null;
            Node<V> childNode = childFlag ? null : node.children.get(c);
            if (childNode == null) {
                childNode = new Node<>(node);
                childNode.character = c;
                node.children = childFlag ? new HashMap<>() : node.children;
                node.children.put(c, childNode);
            }
            node = childNode;

//
//            if (node.children.isEmpty()){
//                Map<Character,Node<V>> children = new HashMap<>();
//                children.put(str.charAt(i),new Node<>(node));
//            }else{
//                Node<V> vNode = node.children.get(str.charAt(i));
//                if (vNode == null){
//                    node.children.put(str.charAt(i),new Node<>(node));
//                }
//            }
        }
        // 已经存在这个单词
        if (node.word) {
            oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        // 不存在单词
        node.value = value;
        size++;
        node.word = true;//设置为单词结尾的标识


        return oldValue;
    }


    /**
     * 删除
     *
     * @param str
     * @return
     */
    V remove(String str) {
        keyCheck(str);
        if (root == null) return null;
        Node<V> node = node(str);
        // 该节点不是单词的结尾
        if (node == null || !node.word) return null;

        size--;
        V oldValue = node.value;
        // 该节点后续还有子结点，只是字符串的中继
        if (node.children != null && !node.children.isEmpty()){
            // 修改该节点的字符串标识
            node.word = false;
            node.value = null;
            return oldValue;
        }

        Node<V> parent = node.parent;
        while (parent != null){
            char c = node.character;
            parent.children.remove(c);
            if (!parent.children.isEmpty()) return oldValue;
            node = parent;
            parent = parent.parent;
        }

        return oldValue;
    }

    /**
     * 是否存在以prefix开头的字符串
     *
     * @param prefix
     * @return
     */
    boolean startsWith(String prefix) {
        Node<V> node = node(prefix);
        return node != null;
    }


    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }


    class Node<V> {
        // 存储父节点
        Node<V> parent;
        // 当前节点存储的字符
        Character character;
        // 该节点的子结点。key 子结点对应的character，value为该节点的子结点node
        Map<Character, Node<V>> children;
        // 标记该节点是否是一个完整的单词
        private boolean word;

        V value;

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }


}
