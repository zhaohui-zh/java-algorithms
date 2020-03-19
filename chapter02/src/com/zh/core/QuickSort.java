package com.zh.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 快速排序
 * Created by zhaohui on 2020/3/19
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l)
            return;
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);

    }

    private void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    private int partition(T[] nums, int l, int h) {
        int i = l;
        int j = h + 1;
        T v = nums[l];
        while (true) {
            while (less(nums[++i], v) && i != h);
            while (less(v, nums[--j]) && j != l);
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] a = {3, 4, 5, 9, 100, 90, 80, 70, 60, 50, 40, 20, 10, 15};
        Sort<Integer> sort = new QuickSort<>();
        sort.sort(a);
        for (Integer i : a) {
            System.out.print(i + " ");
        }
    }
}
