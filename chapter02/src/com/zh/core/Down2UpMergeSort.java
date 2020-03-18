package com.zh.core;

/**
 * 自底向上归并排序
 * Created by zhaohui on 2020/3/18
 */
public class Down2UpMergeSort<T extends Comparable<T>> extends MergeSort<T> {
    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        aux = (T[]) new Comparable[N];

        // size表示此轮归并时有序数组的长度
        // 归并完成后，有序数组的长度变成2倍size，同时也为下一轮归并时有序数组的长度
        // 循环条件: 1) l < N; 2) l+size-1 < N; 3) 1+size < N
        // 合并为一个即: 1+size < N
        // high > middle是因为如果相等，low-middle本身有序，无需和合并
        for (int size = 1; size < N; size *= 2) {
            for (int l = 0; l < N - size; l += 2 * size) {
                merge(nums, l, l + size - 1, Math.min(N - 1, l + 2 * size -1));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {3, 4, 5, 9, 100, 90, 80, 70, 60, 50, 40, 20, 10, 11};
        MergeSort<Integer> mergeSort = new Down2UpMergeSort<>();
        mergeSort.sort(a);
        for (Integer i : a) {
            System.out.print(i + " ");
        }
    }
}
