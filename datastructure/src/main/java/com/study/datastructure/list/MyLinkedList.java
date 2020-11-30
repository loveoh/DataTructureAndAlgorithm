package com.study.datastructure.list;

/**
 * @ClassName MyLinkedList
 * @Description
 * @Author za-yaowei
 * @Date 2020/11/30 10:12
 * @Version 1.0
 */
public class MyLinkedList<E> implements MyList<E> {

    private Node head;
    private int size;


    MyLinkedList() {
        head = new Node(null, null);
    }

    class Node<E> {
        E element;
        Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }


    @Override
    public void clear() {
        head.next = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    @Override
    public void add(Object element) {
        add(size, element);
    }

    @Override
    public E get(int index) {
        return indexOfNode(index).element;
    }

    @Override
    public E set(int index, Object element) {
        Node<E> node = indexOfNode(index);
        Node<E> oldNode = node;
        node.element = (E) element;

        return oldNode.element;
    }

    @Override
    public void add(int index, Object element) {
        rangeCheckForAdd(index);

        Node<E> prevNode = index == 0 ? head : indexOfNode(index - 1);
        Node<E> newNode = new Node<E>((E) element, null);

        newNode.next = prevNode.next;
        prevNode.next = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        // 获取待删除的节点
        Node node = indexOfNode(index);
        Node oldNode = node;
        node.element = node.next.element;
        node.next = node.next.next;
        size--;
        return (E) oldNode.element;
    }

    @Override
    public int indexOf(Object element) {
        Node node = head.next;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (node.element == element) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = head.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node.element);

            node = node.next;
        }
        string.append("]");

//		Node<E> node1 = first;
//		while (node1 != null) {
//
//
//			node1 = node1.next;
//		}
        return string.toString();
    }

    /**
     * 获取指定索引位置的节点对象
     *
     * @param index
     * @return
     */
    private Node<E> indexOfNode(int index) {
        rangeCheck(index);
        Node<E> node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
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
}
