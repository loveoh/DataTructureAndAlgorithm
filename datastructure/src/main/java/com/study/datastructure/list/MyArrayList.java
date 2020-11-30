package com.study.datastructure.list;

import java.util.Objects;

/**
 * @ClassName MyArrayList
 * @Description
 * @Author za-yaowei
 * @Date 2020/11/29 17:46
 * @Version 1.0
 */
public class MyArrayList<E> {


    private int size;

    private E[] elements;

    private static final int DEAFULT_CAPACITY = 2;


    public MyArrayList() {
        this(DEAFULT_CAPACITY);

    }

    public MyArrayList(int capacity) {

        capacity = (capacity < DEAFULT_CAPACITY) ? DEAFULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }


    /**
     * 清除所有元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {

        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void add(E element) {
        add(size,element);
    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {

        rangeCheck(index);
        return elements[index];

    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = get(index);

        elements[index]= element;
        return oldElement;
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 保证数组中能存放size+1 个元素
        ensureCapacity(size + 1 );
        for (int i = size;i>index;i--){
            elements[i] = elements[i -1];
        }
        elements[index] = element;
        size ++ ;
    }

    /**
     * 删除index位置的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = get(index);

        for (int i = index; i < size;i ++){
            elements[i] = elements[i+1];
        }

        size-- ;
        elements[size] = null;
        return oldElement;


    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {

        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) {
                    return i;
                }
            }
        }

        return -1;
    }

//	public int indexOf2(E element) {
//		for (int i = 0; i < size; i++) {
//			if (valEquals(element, elements[i])) return i; // 2n
//		}
//		return ELEMENT_NOT_FOUND;
//	}
//
//	private boolean valEquals(Object v1, Object v2) {
//		return v1 == null ? v2 == null : v1.equals(v2);
//	}

    /**
     * 保证要有capacity的容量
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        int newCapacity =oldCapacity + (oldCapacity >> 1);
        // 创建新数组
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i= 0 ;i<size;i ++){
            newElements[i] = elements[i];
        }
        elements= newElements;
        System.out.println("扩容前：" + oldCapacity +"扩容后：" + newElements.length);
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

    @Override
    public String toString() {
        // size=3, [99, 88, 77]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);

//			if (i != size - 1) {
//				string.append(", ");
//			}
        }
        string.append("]");
        return string.toString();
    }


}
