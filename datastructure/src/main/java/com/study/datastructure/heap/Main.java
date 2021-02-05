package com.study.datastructure.heap;

import com.study.printer.BinaryTrees;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Main {
	
	static void test1() {
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.add(68);
		heap.add(72);
		heap.add(43);
		heap.add(50);
		heap.add(38);
		heap.add(10);
		heap.add(90);
		heap.add(65);
		BinaryTrees.println(heap);
//		 heap.remove();
//		 BinaryTrees.println(heap);
		
		System.out.println(heap.replace(70));
		BinaryTrees.println(heap);
	}
	
	static void test2() {
		Integer[] data = {88, 44, 53, 41, 16, 6, 70 };
		BinaryHeap<Integer> heap = new BinaryHeap<>(data);
		BinaryTrees.println(heap);

		data[0] = 10;
		data[1] = 20;
		BinaryTrees.println(heap);
	}

	static void test3() {
		// 创建小顶堆
		Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
		BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		BinaryTrees.println(heap);
	}

	/**
	 *  top k问题
	 *  使用小顶堆来解决
	 */
	static void test4() {
		// 新建一个小顶堆
		BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		// 找出最大的前k个数
		int k = 7;
		Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
				91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
				90, 6, 65, 49, 3, 26, 61, 21, 48};
		// 前K个数，直接添加到小顶堆中去。
		for (int i = 0; i < data.length; i++) {
			if (i < k ){
				heap.add(data[i]);
			}else if (data[i] > heap.get()){// k+1 个数之后，如果大于小顶堆的对顶元素，就进行替换
				heap.replace(data[i]);
			}
		}
		// O(nlogk)
		BinaryTrees.println(heap);
	}

	public static void main(String[] args) {
		test4();
	}

}
