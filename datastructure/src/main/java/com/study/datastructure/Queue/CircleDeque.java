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
    private int rear;
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
        front = rear = 0;
    }

    void enQueueRear(E element) {

        // todo 数组扩容
        elements[rear + 1] = element;
        rear++;
        size++;
    }

    E deQueueFront() {
        E element = null;
        if (front == rear) {// 表示队列只有一个节点
            element = elements[front];
            front = rear = 0;
        }else{
            element = elements[front];
            front = (front + 1) % elements.length;
        }
        size--;
        return element;
    }

    void enQueueFront(E element) {


        list.add(0);
    }

    E deQueueRear() {
        return (E) list.remove(list.size() - 1);
    }

    E front() {
        return (E) elements[front];
    }

    E rear() {
        return (E) elements[rear];
    }

    int index(int index,int num){

    }
}
