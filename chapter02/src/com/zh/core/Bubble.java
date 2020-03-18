package com.zh.core;

/**
 * 冒泡排序
 * 从左到右不断交换相邻逆序的元素，在一轮的循环之后，可以让未排序的最大元素上浮到右侧。
 * 在一轮循环中，如果没有发生交换，那么说明数组已经是有序的，此时可以直接退出。
 * Created by zhaohui on 2020/3/18
 */
public class Bubble<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        boolean isSorted = false;
        for (int i = N - 1; i > 0 && !isSorted; i--) {
            isSorted = true;
            for (int j = 0; j < i; j++) {
                if (less(nums[j+1], nums[j])) {
                    swap(nums, j+1, j);
                    isSorted = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {4, 3, 9, 2, 10};
        Bubble<Integer> bubble = new Bubble<>();
        bubble.sort(a);
        for (Integer integer : a) {
            System.out.print(integer + " ");
        }
    }
}
