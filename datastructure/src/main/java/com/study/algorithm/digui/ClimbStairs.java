package com.study.algorithm.digui;

/**
 * @ClassName ClimbStairs
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/10 14:49
 * @Version 1.0
 */
public class ClimbStairs {

    int climbStairs(int n){
        if (n <= 2 ){
            return n;
        }
        return climbStairs(n-1) + climbStairs(n-2);
    }


}
