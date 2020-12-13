package com.study.datastructure.Queue;

/**
 * @ClassName CircleQueue
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/13 18:21
 * @Version 1.0
 */
public class CircleQueue<E> {
    // 表示队列的长度
    private int size;
    // 表示队头的索引位置
    private int front;
    // 存储元素的数组
    E[] elements;

    private int CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[CAPACITY];
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
        front = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队 从队列的末尾插入元素
     * front指针有可能指向数组的中间位置，插入的节点必须是 (front + size) %  elements.length
     *
     * @param element
     */
    public void enQueue(E element) {
        // todo 数组扩容
        ensureCapacity(size + 1);
        elements[(front + size) % elements.length] = element;
        size++;

    }

    private void ensureCapacity(int i) {
        if (i < elements.length) {
            return;
        }
        //数组扩容为原先的1.5倍
        E[] newElements = (E[]) new Object[(int) (elements.length * 1.5)];
        for (int index = 0; i < size; index++) {
            newElements[index] = elements[(front + 1) % elements.length];
        }
        elements = newElements;
        //重置头结点
        front = 0;
    }

    /**
     * 出队
     *
     * @return
     */
    public E deQueue() {
        E element = elements[front];
        // 将队头的元素设置为null
        elements[front] = null;
        // 重新设置队头节点
        front = (front + 1) % elements.length;
        size--;
        return element;
    }

    /**
     * 查看队列的首结点
     *
     * @return
     */
    public E front() {
        return elements[front];
    }
}
