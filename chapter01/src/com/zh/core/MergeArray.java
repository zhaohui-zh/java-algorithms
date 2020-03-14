package com.zh.core;

/**
 * 归并两个有序数组
 * 把归并结果存到第一个数组上
 * Created by zhaohui on 2020/3/14
 */
public class MergeArray {

    public static int[] merge(int[] num1, int m, int[] num2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int mergeIndex = num1.length - 1;
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                num1[mergeIndex--] = num2[index2--];
            } else if (index2 < 0) {
                num1[mergeIndex--] = num1[index1--];
            } else if (num1[index1] > num2[index2]) {
                num1[mergeIndex--] = num1[index1--];
            } else {
                num1[mergeIndex--] = num2[index2--];
            }
        }
        return num1;
    }

    public static void main(String[] args) {
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] num2 = {2, 5, 6};
        int n = 3;

        int[] result = merge(num1, m, num2, n);
        for (int i : result) {
            System.out.println(i);
        }
    }

}
