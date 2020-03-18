package com.zh.core;

/**
 * 希尔排序
 * 对于大规模的数组，插入排序很慢，因为它只能交换相邻的元素，每次只能将逆序数量减少 1。
 * 希尔排序的出现就是为了解决插入排序的这种局限性，它通过交换不相邻的元素，每次可以将逆序数量减少大于 1。
 * 希尔排序使用插入排序对间隔 h 的序列进行排序。通过不断减小 h，最后令 h=1，就可以使得整个数组是有序的。
 * Created by zhaohui on 2020/3/18
 */
public class Shell<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40, ...
        }
        while (h >= 1) {  // ..., 40, 13, 4, 1
            for (int i = h; i < N; i++) {
                for (int j = i; j > 0 && less(nums[j], nums[j - h]); j -= h) {
                    swap(nums, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {3, 4, 5, 9, 100, 90, 80, 70, 60, 50, 40, 20, 10};
        for (Integer i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
        Shell<Integer> shell = new Shell<>();
        shell.sort(a);
        for (Integer i : a) {
            System.out.print(i + " ");
        }
    }
}
