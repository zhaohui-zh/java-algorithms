package com.zh.core;

/**
 * 两数之和
 * 在有序数组中找出两个数，使它们的和为 target
 * Created by zhaohui on 2020/3/13
 */
public class TwoSum {

    public static int[] twoSum(int[] a, int target) {
        if (a == null) {
            return null;
        }
        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            int sum = a[i] + a[j];
            if (sum == target) {
                return new int[]{a[i], a[j]};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int target = 9;
        for (int i : twoSum(a, target)) {
            System.out.println(i);
        }
        System.out.println(target);
    }

}
