package com.zh.core;

/**
 * 自顶向下归并排序
 * Created by zhaohui on 2020/3/18
 */
public class Up2DownMergeSort<T extends Comparable<T>> extends MergeSort<T> {
    @Override
    public void sort(T[] nums) {
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);

    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l) {
            return;
        }
        int m = l + (h - l) / 2;
        sort(nums, l, m);
        sort(nums, m + 1, h);
        merge(nums, l, m, h);
    }

    public static void main(String[] args) {
        Integer[] a = {3, 4, 5, 9, 100, 90, 80, 70, 60, 50, 40, 20, 10};
        MergeSort<Integer> mergeSort = new Up2DownMergeSort<>();
        mergeSort.sort(a);
        for (Integer i : a) {
            System.out.print(i + " ");
        }
    }
}
