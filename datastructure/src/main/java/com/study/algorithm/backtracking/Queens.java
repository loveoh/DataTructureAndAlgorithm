package com.study.algorithm.backtracking;

/**
 * @ClassName Queens
 * @Description 八皇后问题
 * @Author za-yaowei
 * @Date 2021/2/10 18:28
 * @Version 1.0
 */
public class Queens {
    public static void main(String[] args) {
        new Queens().placeQueen(8);
    }

    /**
     * 用来存储皇后的位置，索引代表棋盘的行，存储的值代表棋盘的列
     */
    int[] cols;

    /**
     * 符合八皇后的个数
     */
    int num;

    void placeQueen(int n) {
        if (n < 1) return;
        cols = new int[n];
        place(0);
        System.out.println(n + "皇后总共有" + num + "种摆法");


    }


    private void place(int row) {
        if (row == cols.length) {
            num++;
//            show();
            return;
        }

        for (int i = 0; i < cols.length; i++) {
            if (isValid(row, i)) {
                cols[row] = i;
                place(row + 1);
                // 回溯
            }
        }
    }

    /**
     * 剪枝算法
     *
     * @param row
     * @param i
     * @return
     */
    private boolean isValid(int row, int colu) {

        for (int i = 0; i < row; i++) {
            // 判断是否在同一列上
            if (cols[i] == colu) {
                return false;
            }

            if (row - i == Math.abs(colu - cols[i])) {
                return false;
            }
        }

        return true;
    }
}
