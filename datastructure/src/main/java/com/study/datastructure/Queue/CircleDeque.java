package com.study.datastructure.Queue;

import com.study.datastructure.list.MyDoubleLinkedList;

/**
 * @ClassName CircleDeque
 * @Description 循环双端队列
 * @Author za-yaowei
 * @Date 2020/12/15 19:53
 * @Version 1.0
 */
public class CircleDeque<E> {


    // 表示队列的长度
    private int size;
    // 表示队头的索引位置
    private int front;
    // 表示队尾的索引位置
//    private int rear;
    // 存储元素的数组
    E[] elements;

    private int CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[CAPACITY];
    }


    int size() {
        return size;
    }

    boolean isEmpty() {

        return size == 0;
    }

    void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
        front = 0;
    }

    /**
     * 队尾入队
     *
     * @param element
     */
    void enQueueRear(E element) {

        // todo 数组扩容

        elements[front + size - 1] = element;
        size++;
    }

    /**
     * 队头出队
     *
     * @return
     */
    E deQueueFront() {
        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return element;
    }

    /**
     * 队头入队
     *
     * @param element
     */
    void enQueueFront(E element) {
        // todo 数组扩容
        ensureCapacity(size + 1);

        front = index(-1);
        elements[front] = element;
        size++;


    }

    /**
     *  数组扩容
     * @param i
     */
    private void ensureCapacity(int i) {
        if (i <= elements.length){
            return;
        }
        E[] newElements = (E[]) new Object[(int) (elements.length * 1.5)];
        for (int index = 0; i < size; i++ ){
            newElements[i] = elements[front + i];
        }
        elements = newElements;
        // 重置头结点
        front = 0;
    }

    /**
     * 队尾出队
     *
     * @return
     */
    E deQueueRear() {
        E element = elements[(front + size - 1) % elements.length];
        elements[(front + size - 1) % elements.length] = null;
        size--;
        return element;
    }

    E front() {
        return (E) elements[front];
    }

    E rear() {
        return (E) elements[front + size - 1];
    }

    int index(int index) {

        index += front;
        if (index < 0) {
            return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }
}
