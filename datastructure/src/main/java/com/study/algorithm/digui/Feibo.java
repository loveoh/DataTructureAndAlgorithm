package com.study.algorithm.digui;

/**
 * @ClassName Feibo
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/1 12:38
 * @Version 1.0
 */
public class Feibo {

    public static int f1(int i) {
        if (i == 1 || i == 2) {
            return 1;
        }
        return f1(i - 1) + f1(i - 2);
    }

    public static int f2(int i) {
        if (i <=1){
            return i;
        }
        int first =0;
        int sencond =1;

        for (int index = 0; index < i-1; index++) {
            int sum = first + sencond;
            first = sencond;
            sencond = sum;
        }
        return sencond;
    }

    public static void main(String[] args) {
        System.out.println(f1(13));
        System.out.println(f2(13));
    }
}
