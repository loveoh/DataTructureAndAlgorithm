package com.study.algorithm.digui;

/**
 * @ClassName Hanni
 * @Description 汉诺塔问题
 * @Author za-yaowei
 * @Date 2021/2/10 14:52
 * @Version 1.0
 */
public class Hanoi {
    public static void main(String[] args) {
        hanio(3,"A","B","c");
    }

   static void hanio(int n, String p1, String p2, String p3) {
        if (n == 1) {
            move(n, p1, p3);
            return;
        }
        hanio(n - 1, p1, p3, p2);
        move(n, p1, p3);
        hanio(n - 1, p2, p1, p3);

    }

    private static void move(int n, String from, String to) {
        System.out.println(n + "从" + from + "挪到" + to);
    }


}
