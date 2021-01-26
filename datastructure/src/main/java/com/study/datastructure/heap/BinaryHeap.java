package com.study.datastructure.heap;

import com.study.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @ClassName BinaryHeap
 * @Description
 * @Author za-yaowei
 * @Date 2021/1/24 16:29
 * @Version 1.0
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;


    public BinaryHeap(E[] elements, Comparator comparator) {
        if (elements == null || elements.length <= DEFAULT_CAPACITY){
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        }else{
            this.elements = elements;
        }
    }


    public BinaryHeap(Comparator comparator) {
        this(null,comparator);
    }

    public BinaryHeap(E[] elements) {
        this(elements,null);
    }

    public BinaryHeap() {
        this(null,null);

    }


    @Override
    public void clear() {
        size=0;
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }


    /**
     *  每次添加都添加到数组的最后一个元素，然后将添加的元素和父节点元素进行对比（上滤）
     * @param element
     */
    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        // 将元素添加到数组最后
        elements[size] = element;

        siftUp(size);
        size++;
    }

    /**
     * 元素上滤
     * 找到该元素的父元素，进行对比。该元素比父节点大的话，和父节点交换位置，直到父节点的索引小于0 停止循环
     * 元素没有父元素大，则停止循环
     * @param index
     */
    private void siftUp(int index) {
//        E element = elements[index];
//        while (index > 0){
//            //获取父节点
//            int parentIndex = (index - 1) /2;
//            E parentElement = elements[parentIndex];
//            int cmp = compare(element, parentElement);
//            if (cmp <= 0) {
//                break;
//            }
                //交换位置
//            E temp = parentElement;
//            elements[parentIndex] = element;
//            elements[index] = parentElement;
//            index = parentIndex;
//        }

        E element = elements[index];
        while (index > 0){
            //获取父节点
            int parentIndex = (index - 1) /2;
            E parentElement = elements[parentIndex];
            int cmp = compare(element, parentElement);
            if (cmp <= 0) {
                break;
            }
            // 不需要每次都进行交换位置操作。只需要将父节点替换到子结点的位置。
            // 用于拿element进行交换。
            elements[index] = parentElement;
            index = parentIndex;
        }
        // 循环结束时，将element放到index的位置
        elements[index] = element;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    /**
     *  删除二叉堆 就是删除root元素
     *  1、将数组中的最后的元素替换掉根节点元素
     *  2、删除最后元素
     *  3、将替换后的元素和子结点进行比较。
     *      如果node < 最大的子结点，两者替换位置
     *      如果node >= 最大子结点，或者node没有子结点。退出循环。
     *   该循环操作为下滤 O(logn)
     * @return
     */
    @Override
    public E remove() {
        emptyCheck();
        E element = elements[0];
        elements[0] =  elements[size - 1];
        elements[size - 1] = null;
        size--;
        siftDown(0);
        return element;
    }

    /**
     * 下滤操作
     * @param index
     */
    private void siftDown(int index) {
        E element = elements[index];
        while (index < size){
            // 默认使用左子结点
            int childIndex = (index * 2) + 1;
            E child = elements[childIndex];
            int rightChildIndex = childIndex + 1;
            // 右子结点大于左子结点的情况，使用右子结点
            if (rightChildIndex < size && compare(child,elements[rightChildIndex]) < 0 ){
                child = elements[rightChildIndex];
                childIndex = rightChildIndex;
            }
            if (compare(element,child) > 0){
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

    /**
     * 替换二叉堆的根节点
     * @param element
     * @return
     */
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E node = null;
        if (size == 0){
            elements[size++] = element;
        }else {
            elements[0] = element;
            node = element;
            siftDown(0);
        }
        return node;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int)node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int)node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int)node];
    }
}
