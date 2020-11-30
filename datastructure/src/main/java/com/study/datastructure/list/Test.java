package com.study.datastructure.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName Test
 * @Description
 * @Author za-yaowei
 * @Date 2020/8/31 11:10
 * @Version 1.0
 */
public class Test {


    public static void main(String[] args) {

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();

        linkedList.add(3);
        System.out.println(linkedList.get(0));
        linkedList.add(1,22);
        linkedList.add(0,221);
        linkedList.remove(0);
        System.out.println(linkedList);

//        MyArrayList<Integer> arrayList = new MyArrayList<>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
//        arrayList.add(4);
//
////        arrayList.remove(-1);
//
//        int index = arrayList.indexOf(3);
//        System.out.println(index);
//        System.out.println(arrayList.contains(2));
//        arrayList.clear();
//
//        System.out.println(arrayList.size());
//        System.out.println(arrayList.isEmpty());
//
//
////        arrayList.add(2,11);
////        arrayList.remove(0);
//
//        System.out.println(arrayList);


    }

}
