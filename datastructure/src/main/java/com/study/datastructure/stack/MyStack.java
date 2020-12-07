package com.study.datastructure.stack;

import com.study.datastructure.list.MyArrayList;

/**
 * @ClassName MyStack
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/6 16:03
 * @Version 1.0
 */
public class MyStack<E> {

    private MyArrayList list = new MyArrayList();

    public int size (){
        return list.size();
    }


    public boolean isEmpty(){
        return  list.isEmpty();
    }

    public void push(E element){

         list.add(element);
    }

    // 弹出栈顶元素
    public E pop(){
        return (E) list.remove(list.size() -1);
    }
    // 获取栈顶元素
    public E top(){
        return (E) list.get(list.size() -1 );
    }

}
