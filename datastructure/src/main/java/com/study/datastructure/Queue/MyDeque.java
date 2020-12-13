package com.study.datastructure.Queue;

import com.study.datastructure.list.MyDoubleLinkedList;

/**
 * @ClassName MyDeque
 * @Description 双端队列表示，可以在队头和队尾添加或删除结点，所以称为双端队列
 * @Author za-yaowei
 * @Date 2020/12/11 12:27
 * @Version 1.0
 */
public class MyDeque<E> {

    MyDoubleLinkedList list = new MyDoubleLinkedList();

    int size() {
        return list.size();
    }

    boolean isEmpty() {
        return list.isEmpty();
    }

    void clear() {
        list.clear();
    }

    void enQueueRear(E element) {
        list.add(element);
    }

    E deQueueFront() {
        return (E) list.remove(0);
    }

    void enQueueFront(E element){
        list.add(0);
    }

    E deQueueRear(){
        return (E) list.remove(list.size()-1);
    }

    E front(){
        return (E) list.get(0);
    }
    E rear(){
        return (E) list.get(list.size()-1);
    }
}
