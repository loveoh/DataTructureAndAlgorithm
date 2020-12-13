package com.study.leetcode.链表;

import org.springframework.util.CollectionUtils;

import java.util.Stack;

/**
 * @ClassName 用栈实现队列_232
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/7 20:59
 * @Version 1.0
 */
public class 用栈实现队列_232 {
    /**
     * 使用两个栈进行操作。
     * 添加队列元素时，往inStack中添加元素
     * 出队的时候，首先判断 outStack是否为空，如果为空，将inStack中的元素全部弹出，并放入outStack中。然后再弹出outStack中的栈顶元素
     *
     *
     */
    private Stack<Integer> inStack = new Stack<>();

    private Stack<Integer> outStack = new Stack<>();

    /** Initialize your data structure here. */
    public 用栈实现队列_232() {
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 首先判断是否是空
        if (outStack.size() == 0){
            // 将inStack中的元素全部放入到outStack中，然后再进行弹栈操作
            while (inStack.size() != 0){
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
// 首先判断是否是空
        if (outStack.size() == 0){
            while (inStack.size() != 0){
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (inStack.size() == 0 && outStack.size() == 0){
            return true;
        }
        return false;
    }
}
