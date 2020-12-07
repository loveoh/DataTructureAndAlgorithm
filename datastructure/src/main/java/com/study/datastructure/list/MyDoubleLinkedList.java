package com.study.datastructure.list;

import com.study.leetcode.链表.ListNode;

/**
 * @ClassName 双向链表
 * @Description
 * @Author za-yaowei
 * @Date 2020/11/30 10:12
 * @Version 1.0
 */
public class MyDoubleLinkedList<E> implements MyList<E> {

    private Node first;
    private Node last;
    private int size;


//    MyDoubleLinkedList() {
//        first = new Node(null, null);
//    }

    class Node<E> {
        E element;
        Node next;
        Node prve;

        public Node(E element, Node next,Node prve) {
            this.element = element;
            this.next = next;
            this.prve = prve;
        }
    }


    @Override
    public void clear() {
        first.next = null;
        last.next = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    @Override
    public void add(Object element) {
        add(size, element);
    }

    @Override
    public E get(int index) {
        return indexOfNode(index).element;
    }

    @Override
    public E set(int index, Object element) {
        Node<E> node = indexOfNode(index);
        Node<E> oldNode = node;
        node.element = (E) element;

        return oldNode.element;
    }

    @Override
    public void add(int index, Object element) {
        rangeCheckForAdd(index);

        if(size == index){
            Node oldLast = last;
            last = new Node<E>((E) element,null,oldLast);
            if (oldLast == null){//表示是空链表，开始添加第一个元素
                first = last;
            }else{
                oldLast.next = last;
            }
        }else{
            // 当前节点变成插入结点的下一个结点
            Node next = indexOfNode(index);
            // 获取插入结点的前驱结点
            Node prev = next.prve;
            Node node = new Node(element,next,prev);
            next.prve = node;

            if (prev == null){ // 表示插入的是头结点
                first = node;
            }else {
                prev.next = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        // 获取待删除的节点
        Node node = indexOfNode(index);
        // 待删结点的后继结点
        Node next = node.next;
        // 待删结点的前驱结点
        Node prev = node.prve;


        if (prev == null){// 表示删除的是第一个结点
            first = next;
        }else {
            prev.next = next;
        }

        if (next == null){ //表示删除的是最后一个节点
            last = prev;
        }else{
            next.prve = prev;
        }
        size--;
        return (E) node.element;
    }

    @Override
    public int indexOf(Object element) {
        Node node = first.next;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (node.element == element) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node.element);

            node = node.next;
        }
        string.append("]");

//		Node<E> node1 = first;
//		while (node1 != null) {
//
//
//			node1 = node1.next;
//		}
        return string.toString();
    }

    /**
     * 获取指定索引位置的节点对象
     *
     * @param index
     * @return
     */
    private Node<E> indexOfNode(int index) {
        rangeCheck(index);
        Node<E> node;
        if (index < size / 2){
            node = first.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }else{
            node = last.next;
            for (int i = size-1;i>index;i--){
                node = node.prve;
            }
        }
        return node;
    }


    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}
