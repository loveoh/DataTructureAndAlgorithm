package com.study.datastructure.list;

/**
 * @ClassName 单向循环链表
 * @Description
 * @Author za-yaowei
 * @Date 2020/11/30 10:12
 * @Version 1.0
 */
public class SingleCircleLinkedList<E> implements MyList<E> {

    private Node head;
    private int size;



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

        if(index == 0){
            Node<E> newNode = new Node<E>((E) element, head);
            // 获取最后一个节点
            // 当size ==0 时，表示该链表是空。那该节点的last就指向自己。
            Node<E> last = (size == 0 )? newNode : indexOfNode(size-1);
            last.next = newNode;

        }else{
            Node<E> prevNode =  indexOfNode(index - 1);
            Node<E> newNode = new Node<E>((E) element, prevNode.next);
            prevNode.next = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node oldNode = head;

        if (index == 0 ){// 表示删除第一个元素
            if (size == 1){// 表示链表只有一个参数
                head = null;
            }else{
                // 获取链表最后一个节点
                Node<E> last = indexOfNode(size -1 );
                head = head.next;
                last.next = head;
            }

        }else{
            Node<E> node = indexOfNode(index -1 );
            oldNode = node;
            node.next = node.next.next;
        }


//
//        // 获取待删除的节点
//        Node node = indexOfNode(index);
//        Node oldNode = node;
//        node.element = node.next.element;
//        node.next = node.next.next;
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
