package com.study.datastructure.Queue;

import com.study.datastructure.list.MyDoubleLinkedList;
import com.study.datastructure.list.MyLinkedList;

/**
 * @ClassName MyQueue
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/7 16:54
 * @Version 1.0
 */
public class MyQueue<E> {

    private MyDoubleLinkedList<E> list = new MyDoubleLinkedList<>();


    public int size(){
        return list.size();
    }

    public void clear(){
        list.clear();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     *  入队
     * @param element
     */
    public void enQueue(E element){
        list.add(element);
    }

    /**
     *  出队
     * @return
     */
    public E deQueue(){
        return list.remove(0);
    }

    /**
     *  查看队列的首结点
     * @return
     */
    public E front(){
        return list.get(0);
    }


}
