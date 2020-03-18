package com.zh.core;

/**
 * 选择排序
 * 从数组中选择最小元素，将它与数组的第一个元素交换位置。再从数组剩下的元素中选择出最小的元素，
 * 将它与数组的第二个元素交换位置。不断进行这样的操作，直到将整个数组排序。
 * Created by zhaohui on 2020/3/18
 */
public class Selection<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        for (int i = 0; i < N - 1; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(nums[j], nums[min])) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {3, 6, 9, 2, 4};
        Selection<Integer> selection = new Selection<>();
        selection.sort(a);
        for (Integer integer : a) {
            System.out.print(integer + " ");
        }
    }
}
