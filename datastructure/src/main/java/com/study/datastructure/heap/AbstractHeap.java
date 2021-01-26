package com.study.datastructure.heap;

import java.util.Comparator;

/**
 * @ClassName AbstractHeap
 * @Description
 * @Author za-yaowei
 * @Date 2021/1/26 11:14
 * @Version 1.0
 */
public abstract class AbstractHeap<E> implements Heap<E>{

    protected int size;
    private Comparator comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int compare(E e1 ,E e2){
        return comparator != null ? comparator.compare(e1,e2) : ((Comparable<E>)e1).compareTo(e2);
    }

}
