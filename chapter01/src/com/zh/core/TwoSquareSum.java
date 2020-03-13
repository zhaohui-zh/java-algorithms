package com.zh.core;

/**
 * 两数平方和
 * 判断一个非负整数是否为两个整数的平方和
 * Created by zhaohui on 2020/3/14
 */
public class TwoSquareSum {

    public static boolean judgeSquareSum(int a) {
        if ( a < 0)
            return false;
        int i = 0;
        int j = (int) Math.sqrt(a);
        while ( i <= j) {
            int sum = i * i + j * j;
            if (sum == a) {
                System.out.println(i);
                System.out.println(j);
                return true;
            } else if (sum < a) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int a = 82;
        System.out.println(judgeSquareSum(a));
    }

}
